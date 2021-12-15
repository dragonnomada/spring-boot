package com.example.delivery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transport {

	@Id @GeneratedValue
	@Getter @Setter
	private Long id;
	
	@Column(unique = true, nullable = false)
	@Getter @Setter
	private String name;
	
}
