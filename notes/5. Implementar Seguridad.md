# Implementar una Capa de Seguridad

## Introducción

Por defecto Spring Web no restringe el acceso a las rutas establecidas por los controladores. Esto significa, que cualquier usuario podría consumir los servicios expuestos por los controladores. Para garantizar el uso correcto de las API podemos establecer una capa de seguridad basada en **autenticación** y **autorización**.

La *autenticación* consiste en brindar credenciales lógicas (un token de acceso) a partir de la verificación de credeciales físicas (usuario/contraseña). Mediante Json Web Token (`JWT`) podemos generar un conjunto de credenciales lógicas que guarden el perfil, el nombre, la expiración, etc. Para poder identificar
el usuario cada que consume un API. Esta capa permite que el *token* sea la llave **Bearer** requerida en cada llamada a cualquier ruta de api del sistema (salvo las expuestas manualmente). Esto garantiza que el servidor quede completamente blindado y aislado del exterior, a menos que se posea una llave válida y vigente.

Por otro lado la *autorización* permite restringir las rutas (punto finales que también podrían ser métodos de servicios) mediante la pre-autorización del perfil (el rol de usuario) o algunas propiedades más avanzadas, por ejemplo, una pos-autorización que verifique integridad de los datos salientes. Esto permite un mayor control sobre las rutas, permitiendo pre y pos autorizaciones sobre los usuarios. Sin importar que el usuario tenga una llave válida, también se le pedirá tener un perfil lógico válido para el consumo de las transacciones.

## Pasos 1. Configurar las dependencias.

> Además de *Spring Security*, agregaremos sobre el `pom.xml` las dependencias de maven

```xml
<!-- Se usa para encriptar/desencriptar el token -->
<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>

<!-- Se usa para serializar/deserializar el token -->
<!-- https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api -->
<dependency>
    <groupId>javax.xml.bind</groupId>
    <artifactId>jaxb-api</artifactId>
</dependency>
```

## Paso 2. Generar las clases de configuración de seguridad

> La clase `GlobalSecurityConfig`

    Es responsable de configurar las pre/pos autorizaciones marcadas sobre las rutas

```java
package com.example.app.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

// Habilita las anotaciones de roles a rutas @PreAuthorize
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class GlobalSecurityConfig extends GlobalMethodSecurityConfiguration {

	// TODO: Alterar el comportamiento nativo del GlobalMethodSecurityConfiguration
	
}
```

>  La clase `SecurityConfig`

    Es responsable de configurar la seguridad `http` (mediante un filtro JWT) y exponer las rutas públicas

```java
package com.example.app.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Modificamos la configuración de la seguridad HTTP para implementar JSON Web Tokens
		http.csrf().disable() // Deshabilitamos el CSRF (Cross-Site Request Forgery) con fines de pruebas y desarrollo
			// Agregamos el filtro personalizado de JWT con soporte a roles
			.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			// TODO: Agregar rutas públicas que no requieran token (limitar a recursos públicos y logins)
			.antMatchers(HttpMethod.GET, "/public/**").permitAll()
			.antMatchers(HttpMethod.GET, "/error").permitAll()
			//.antMatchers(HttpMethod.GET, "/admin").hasRole("ROLE_ADMIN")
			//	.and().hasRole("ROLE_USER")
			.antMatchers(HttpMethod.POST, "/secure/login").permitAll()
			.anyRequest().authenticated();			
	}
	
}
```

> La clase `JWTAuthorizationFilter`

    Es responsable de configurar el protocolo JWT y los metadatos dentro del token (por ejemplo, los roles)

```java
package com.example.app.security.config;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
	
    // Establecemos la forma en la que se consumirá/validará el token
	public static final String HEADER = "Authorization"; // Header requerido
	public static final String PREFIX = "Bearer "; // Prefijo del token
	// TODO: Modificar la clave secreta para provenir de un archivo o variables de entorno
	public static final String SECRET = "SECRET_PRIVATE_KEY_123456789----XXXXXX---YYYY"; // Private Key
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		try {
			if (existeJWTToken(request, response)) { // Recupera el token del HEADER/Authorization
				Claims claims = validateToken(request); // Recupera los metadatos del token (roles/claims)
				if (claims.get("authorities") != null) { // Verificamos que tenga a las autoridades
					setUpSpringAuthentication(claims); // Validamos la autenticación
				} else {
					SecurityContextHolder.clearContext();
				}
			} else {
                SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response); // Aplica el filtro de autorización
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			return;
		}
	}	

    private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(HEADER); // Resuelve si el token está en los headers
		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
			return false;
		return true;
	}

	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(HEADER).replace(PREFIX, ""); // Recupera el token del HEADER/Authorization
		return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody(); // Deserializa el token
	}

	private void setUpSpringAuthentication(Claims claims) {
		@SuppressWarnings("unchecked")
		List<String> authorities = (List<String>) claims.get("authorities"); // Recuperamos la información de las autoridades

        // Establecemos la autoridad basada en roles
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

		SecurityContextHolder.getContext().setAuthentication(auth); // Ajustamos el contexto de autorización
	}

}
```

## Paso 3. Configurar el modelo, repository, servicio y controlador para el inicio de sesión

> Modelo del Usuario de Seguridad

```java
package com.example.app.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecurityUser {

	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String username;
	
	@Getter @Setter
	private String token;
	
	@Getter @Setter
	private String roles;
	
	public long calculateExpirationMillis() {
		// TODO: Dependiendo los roles del usuario, calcular el tiempo de expiración
		return 60000l;
	}
	
}
```

> El servicio de seguridad con métodos para el inicio de sesión

    Provee la validación de un usuario/contraseña en el sistema, la generación de un token, la búsqueda de usuarios

```java
package com.example.app.security.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.example.app.security.config.JWTAuthorizationFilter;
import com.example.app.security.model.SecurityUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SecurityService {

	// TODO: Agregar más servicios de seguridad
	
	public boolean isValid(String username, String password) {
		// TODO: Validar usuario y contraseña con la base de datos

		if (username.equals("batman") || username.equals("robin") || username.equals("alfred")) {
			if (password.equals("baticueva")) {
				return true;
			}
		}
		
		return false;
	}
	
	public SecurityUser getUser(String username) {
		// TODO: Obtener los datos del usuario y sus roles desde la base de datos
		
		String roles = "";
		
		if (username.equals("batman")) {
			roles = "ROLE_ADMIN";
		} else if (username.equals("robin")) {
			roles = "ROLE_USER";
		} else if (username.equals("alfred")) {
			roles = "ROLE_ADMIN,ROLE_USER";
		}
		
		SecurityUser user = SecurityUser.builder()
			.username(username)
			.roles(roles)
			.build();
		
		return user;
	}
	
	public String generateToken(SecurityUser user) {
		
		// TODO: Modificar la forma de generar el token
		
		String secretKey = JWTAuthorizationFilter.SECRET;
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(user.getRoles());
		
		String token = Jwts
				.builder()
				.setId("com.example")
				.setSubject(user.getUsername())
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + user.calculateExpirationMillis()))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();
		
		user.setToken(token);

		// TODO: Actualizar en base de datos el nuevo token del usuario
		
		System.out.println(String.format("username: %s | Bearer %s", user.getUsername(), token));

		return JWTAuthorizationFilter.PREFIX + token;
		
	}
	
}
```

> El controlador de la ruta para iniciar sesión `POST /secure/login`

    Provee una ruta pública (expuesta en `SecurityConfig`) para iniciar sesión con las credenciales físicas (usuario/contraseña)

```java
package com.example.app.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.app.security.model.SecurityUser;
import com.example.app.security.service.SecurityService;

@RestController
@RequestMapping("/secure")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class SecurityController {

	@Autowired
	SecurityService securityService;
	
	@PostMapping("/login")
	public SecurityUser signIn(@RequestParam String username, @RequestParam String password) {
		// TODO: Validar si el usuario y contraseña son correctas (Hint: Usar base de datos)
        
        if (securityService.isValid(username, password)) {			
			// Si el usuario es válido, le generamos un token
			
			// Este usuario debería venir de un reporitorio desde la base de datos a través de otro servicio
			SecurityUser user = securityService.getUser(username);
			
			securityService.generateToken(user);
			
			return user;
		}
		
        // Generamos manualmente un HTTP Status 500 (Internal Server Error) con un mensaje personalizado para el cliente
		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "El usuario " + username + " no es válido");
	}
	
}
```

## Paso 4. Pre/Pos Autorizar las rutas finales, según los perfiles/roles de los usuarios

> En la clase del controlador tipo api, tipo vista o en algún servicio

    Podemos anotar @PreAuthorize o @PostAuthorize para determinar las reglas de autorización basados en el filtro de seguridad
    lo más común es usar `hasRole('PERFIL_USUARIO') or/and hasAuthority('MY_COMPANY')`

```java
    @GetMapping("/hello")
    public String getHello() {
        return "Hello world";
    }

//@PreAuthorize("hasRole('ROLE_ADMIN') and hasAuthority('com.example')")
//@PostAuthorize("hasRole('ROLE_DELIVERY') and hasAuthority('com.dhl')")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@GetMapping("/admin")
public String getHelloAdmin() {
    return "Hola administrador";
}

@PreAuthorize("hasRole('ROLE_USER')")
@GetMapping("/user")
public String getHelloUser() {
    return "Hola usuario";
}

@PreAuthorize("hasRole('ROLE_ADMIN') AND hasRole('ROLE_USER')")
@GetMapping("/super")
public String getHelloSuper() {
    return "Hola super usuario (administrador y usuario)";
}

@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
@GetMapping("/generic")
public String getHelloAdminUser() {
    return "Hola usuario normal o administrador";
}
```