package com.example.delivery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Package {

	@Id @GeneratedValue
	@Getter @Setter
	private Long id;
	
	@Column(unique = true, nullable = false)
	@Getter @Setter
	private String guid;
	
	@JsonBackReference
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id") // table package -> column store_id
	@Getter @Setter
	private Store store; // Lazy: getStore()
	
}
