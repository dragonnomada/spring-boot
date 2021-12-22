package com.example.file_vault.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File {

	@Id @GeneratedValue
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String path;
	
	@Getter @Setter
	private String filename;
	
	@Getter @Setter
	private Long size;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_file_id")
	private User user;
	
}
