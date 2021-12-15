package com.example.mysql;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String title;
	private boolean checked;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "create_at")
	private Date createAt;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "update_at")
	private Date updateAt;
	
}
