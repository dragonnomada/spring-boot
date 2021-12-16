# Crear Repositorios

## Introducción

Los repositorios son interfaces transaccionales hacia modelos/entidades, para poder
hacer que persistan en algún lugar cómo bases de datos. Spring provee herramientas
como JPA en las que se basa Hibernate. JPA es una capa que nos permite hacer operaciones
CRUD de una manera más imperativa. Es decir, nos obliga a formalizar las trasacciones en
forma de Queries.

Hibernate provee una gramática para soportar múltiples bases de datos, sin tener que 
redefinir los queries, o al menos acortarlos.

Con todo esto, podemos nombrar nuestros queries y comenzar a trabajar con bases de datos
y entidades.

Así JPA usará toda la información disponible en las entidades para realizar las transacciones
de forma correcta. Podemos marcar en los @Entity, el @Table y en sus campos @Column, @Id, etc.
También relaciones como @OneToOne, @ManyToOne, @OneToMany, @ManyToMany.

## Paso 1. Crear la base de datos y el usuario

> Crear la base de datos

```sql
create database my_db;
```

> Crear el usuario

```sql
create user 'my_user'@'%' identified by 'my_password';
```


> Otorgar permisos al usuario

```sql
grant all on my_db.* to 'my_user'@'%';
```

* **Nota:** `%` significa que el usuario se puede conectar de forma local, `x.x.x.x` significaría
poder conectarse desde esa IP, y `*` poder conectarse desde cualquier IP o dominio.

## Paso 2. Instalar las dependencias

* **Spring JPA**
* **H2 Hibernate**
* **MySQL Driver**

## Paso 3. Configurar las propiedades del proyecto

> En `src/main/resources/application.properties`

```txt
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/my_db
spring.datasource.username=my_user
spring.datasource.password=my_password
spring.datasource.driver-class-name =com.mysql.jdbc.Driver
spring.jpa.show-sql: true
```

## Paso 4. Definir un Modelo/Entidad

> Crear la clase entidad Product

```java
@Entity
public class Product {

  @Id @GeneratedValue
  @Getter @Setter
  private Long id;

  @Getter @Setter
  private String name;

  @Getter @Setter
  private Double price;

}
```

## Paso 5. Definir el Repositorio

> Crear la interfaz de consulta ProductRepository

```java
public interface ProductRepository extends CrudRepository<Product, Long> {

  @Query("select product from Product product where product.price < ?1")
  public List<Product> findProductsLessThanPrice(double maxPrice);
 
}
```

## Paso 6. Usar el Repositorio

	**Adevertencia**: Usa los repositorios sólo dentro de los servicio, para
	que el proyecto sea seguro. Si los controladores usan directamente los
	repositorios, el mantenimiento a la lógica de negocios se incrementará.
	Por ejemplo, si en algún momento se pide una transacción previa al consumo
	de un query, el controlador podría no saberlo y ocasionar errores lógicos.

> Enlazar el Repositorio

```java
@Autowired
ProductRepository productRepository;
```

> Consumir el Repositorio

```java
List<Product> productSales = productRepository.findProductsLessThanPrice(10.5);

Iterable<Product> products = productRepository.findAll();

Product product = new Product();

productRepository.save(product); // insert

Product firstProduct = products.get(0);

firstProduct.setPrice(firstProduct.getPrice() * 0.9);

productRepository.save(firstProduct); // update
```








