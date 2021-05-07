package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//Unique Table Constraints
@Table(name = "users", uniqueConstraints={@UniqueConstraint(columnNames ={"fName", "lName"})})
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String fName;
	@Column
	private String lName;
	
	@JsonCreator
	public User(@JsonProperty("fName")String fName, @JsonProperty("lName")String lName) {
		this.fName = fName;
		this.lName = lName;
	}

	public User() {
		super();
	}
	
	/*
	 * Getters and Setters
	 */
	public String getFirstname() {
		
		return this.fName;
	}
	public String getLastName() {
		
		return this.lName;
	}
	
	public int getId() {
		return this.id;
	}
	
	
	
}
