package com.restfull.app.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "category")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "categoria_id")
	private int id;
	
	@Column(length = 45)
	private String name;
	
	@Column(length = 250)
	private String description;
	
	@OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "category")
	@JsonIgnoreProperties("category")
	public List<Disfraz> costumes;
	

	//===  Getters and Setters ======//


	
	
	public int getId() {
		return id;
	}

	public List<Disfraz> getCostumes() {
		return costumes;
	}

	public void setCostumes(List<Disfraz> costumes) {
		this.costumes = costumes;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
