# Crear Vistas

## Introducción

Cuándo nuestros sistemas exponen interfaces de usuario (Vistas), estas generalmente se basan en HTML,
por su simplicidad, facilidad de contrucción y adaptabilidad de diseño. Y a través de Thymeleaf podemos
potenciar el desarrollo, comunicando datos desde el controlador visual, hasta la vista.

Generalmente cada vista será una plantilla (que puede fragmentarse). Y su finalidad será
consumir los datos del modelo/atributo expuestos en el controlador (de forma global o unitaria)
y con esto generar vistas interactivas con el usuario, quién a través de otros modelos/entidades
devuelva información que alimente al sistema.

Por ejemplo, un usuario podría consultar datos de productos, crear nuevos productos, editar productos
existentes o eliminarlos. Con todo esto, podemos crear sistemas administrativos o de uso general
para crear aplicaciones web enriquecidas con nuestro servicios.

## Paso 1. Instalar la dependencia a Thymeleaf

> Configurar la dependencia `Thymeleaf`

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

## Paso 2. Configurar una plantilla por cada vista ofrecida al usuario

> Cada plantilla será un archivo html dentro de `src/main/resources/templates/<name>.html`

```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>Thymeleaf Demo</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>
    <h1 th:text="${title}">TITLE</h1>
</body>

</html>
```

## Paso 3. Crear un controlador visual (@Controller)

> Similar al Controlador de API, crearemos un controlador simple `@Controller`

```java
@Controller
public class MyViewController {

    // TODO: Configura las rutas visuales

}
```

## Paso 4. Configurar las rutas visuales (@GetMapping, @PostMapping, ...)

> Generalmente para las vistas sólo se utiliza `@GetMapping` y `@PostMapping`

```java
@GetMapping("/view/products/{id}/edit")
public String editProductView(@PathVariable int id, Model model) {
    model.addAttribute("productId", id);
    return "ProductEditForm";
}
```

* **Nota:** Observa que las rutas visuales devuelven un *String* el cuál contiene la ruta de la plantilla,
es decir, el nombre de la vista a mostrar o una redirección hacia otra ruta, por ejemplo, 
`redirect:/product/error?message=Product Not Found`.

## Paso 5. Recuperar un atributo dentro de la vista de Thymeleaf

> Podemos acceder a los atributos modelados a través de `${<attributo/modelo/objeto/...>}`

```html
<img th:src="${picture}" src="default.jpg">
```

> Del lado del controlador tendríamos un atributo llamdo `picture` que puede ser global o local.

```java
// Gloabal (Estáticas o de Sesión)
@ModelAttribute("picture")
public String getPicture() {
    return "https://myexample.org/avatar.png"
}

// Local (Dinámicas o Petición)
@GetMapping("/my-avatar/${id}")
public String getView(@PathVariable int id, Model model) {
    model.addAttribute("picture", "https://myexample.org/avatar_" + id + ".png")
    return "<view name>"
}
```






