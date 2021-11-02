package com.restfull.app.entity;




import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
*
* @author andres
*/
@Entity
@Table(name = "costume")
public class Disfraz {

	/** variable id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "disfraz_id")
	private int id;	
	
	/** variable name */
	@Column(length = 45)
	private String name;
	
	/** variable brand */
	@Column(length = 45)
	private String brand;
	
	private String imagen;
	
	private boolean reserva;
	
	/** variable year */
	private int year;
	
	/** variable description */
	@Column(length = 250)
	private String description;
	
	/** variable category */
	@ManyToOne
	@JoinColumn(name="categoria_id")
	@JsonIgnoreProperties("costumes")	
	private Categoria category;
	
	
	/** variable messages */	
	@OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "costume")
	@JsonIgnoreProperties({"costume","client"})
	private List<Mensaje> messages;
	
	/** variable reservation */
	@OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "costume")
	@JsonIgnoreProperties("costume")
	private List<Reservacion> reservations;
	
	
	
	
	
	
	
	
	
	
	//===  Getters and Setters ======//
	
	
	


	public Categoria getCategory() {
		return category;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public boolean isReserva() {
		return reserva;
	}

	public void setReserva(boolean reserva) {
		this.reserva = reserva;
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

	public void setCategory(Categoria category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
