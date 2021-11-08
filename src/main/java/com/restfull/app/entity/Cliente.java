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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "client")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idClient")
	private int idClient;
	
	@Column(length = 45)
	private String email;
	
	//@Column(length = 45)	
	private String password;
	
	private boolean admin = false;
	
	@Column(length = 250)
	private String name;	
	
	
	private int age;
	
	
	@OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "client")
	@JsonIgnoreProperties("client")
	private List<Mensaje> messages;
	
	@OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "client")
	@JsonIgnoreProperties("client")
	private List<Reservacion> reservations;
	
	
	
	
	
	//===  Getters and Setters ======//
	
	
	

	public int getIdClient() {
		return idClient;
	}




	public boolean isAdmin() {
		return admin;
	}




	public void setAdmin(boolean admin) {
		this.admin = admin;
	}




	public List<Reservacion> getReservations() {
		return reservations;
	}




	public void setReservations(List<Reservacion> reservations) {
		this.reservations = reservations;
	}




	public List<Mensaje> getMessages() {
		return messages;
	}




	public void setMessages(List<Mensaje> messages) {
		this.messages = messages;
	}




	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
