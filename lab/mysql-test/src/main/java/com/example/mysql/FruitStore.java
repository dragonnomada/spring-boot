package com.example.mysql;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FruitStore {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	@OneToMany
	private List<Fruit> fruits;
	
}
