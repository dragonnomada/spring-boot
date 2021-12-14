# Crear Servicios

## Introducción

Los servicios son interfaces con múltiples implementaciones que ofrecen funcionalidad/transacciones 
sobre nuestros modelos, para que los controladores puedan utilizarlos de forma segura.

## Paso 1. Definir un servicio

> Crear el Servicio

```java
package com.example.demo.service;

public interface MyService {

  public List<MyModel> getAll();

}
```

> Implementar el Servicio

```java
package com.example.demo.service.impl;

public class MyServiceLocal {

  public List<MyModel> getAll() {
    return ...;
  }   

}
```

## Paso 2. Configurar el Servicio

> Crear el archivo de configuración y conectar los @Bean

```java
package com.example.demo.config;

@Configuration
public class MyConfig {

  @Bean
  public MyService myService() {
    MyServiceLocal myServiceLocal = new MyServiceLocal(...);

    // TODO: Configurar el servicio

    return (MyService)myServiceLocal;
  }

}
```

## Paso 3. Utilizar el Servicio

> Conectar en un controlador o en una prueba unitaria

```java
package com.example.demo.controller.api;

@RestController
public class MyController {
   
  @Autowired
  MyService myService;

  @GetMapping("/api/mymodel")
  public List<MyModel> getAll() {
    // TODO: Consumir los servicios enlazados y usarlos
    return myService.getAll();
  }

}
```







