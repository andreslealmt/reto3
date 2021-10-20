package com.restfull.app.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "calficaciones")
public class CalificacionReserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
		
	private int calificacion;
	
	@Column(length = 250)
	private String mensaje;
	
	
	
	@JsonIgnoreProperties({"client","costume","status","devolutionDate","startDate","score"})
	@OneToOne(mappedBy = "score")
	private Reservacion reservation;
	
	
	//Getters and Setters
	
	

	public int getId() {
		return id;
	}

	public Reservacion getReservation() {
		return reservation;
	}

	public void setReservation(Reservacion reservation) {
		this.reservation = reservation;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}	
	
	

}
