package com.example.jpa_demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data // Auto-generate: Getters Setters toString ...

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Package {

	@Id // primary key
	@GeneratedValue // hibernate-sequence | auto increment
	@Getter @Setter
	private Long id;
	
	@Column(unique = true)
	@Getter @Setter
	private String guid;
	
	/*
	public String getGuid() {
		if (this.id == 0) {
			this.guid = "123";// RANDOM GUID
		}
		return this.guid;
	}
	*/
	
}

// create table package (
//   package_id int(22) primary key,
//   guid varch(255)
// );

/*
 * JPA: Es una tecnología que permite mapear/transformar
 * las entidades definidas en código (clases POJO) 
 * a tablas en la base de datos 
 * 
 * Algunas convenciones de nombrado serían:
 * 
 * class Item -> table item
 * class MyItem -> table my_item
 * 
 * Long id -> column id
 * String fullName -> column full_name
 * 
 * Las convenciones de nombrado pueden ser alteradas explícitamente:
 * 
 * @Table(name="my_items")
 * class Item
 * 
 * @Column(name="fname")
 * String fullName
 * 
 * JPA Soporta múltiples anotaciones para guiar la construcción:
 * 
 * @Id
 * @GeneratedValue
 * @OneToOne
 * @OneToMany
 * @ManyToOne
 * @ManyToMany
 * @JoinColumn
 * @JsonIgnore
 * ...
 * 
 */
