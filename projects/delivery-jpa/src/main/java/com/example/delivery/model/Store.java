package com.example.delivery.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

	@Id @GeneratedValue
	@Getter @Setter
	private Long id;
	
	@Column(unique = true, nullable = false)
	@Getter @Setter
	private String name;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "store", 
			cascade = CascadeType.ALL, 
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	@Getter @Setter
	private List<Package> packages; // Lazy: getPackages()
	
}
