package com.restfull.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "reservation")
public class Reservacion implements Serializable{	
	
	
	private static final long serialVersionUID = -8475557567512560738L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReservation;
	
	private String startDate;
	
	private String devolutionDate;
	
	private String status = "created";
	
	@ManyToOne
	@JoinColumn(name="disfraz_id")
	@JsonIgnoreProperties("reservations")
	private Disfraz costume;
	
	@ManyToOne
	@JoinColumn(name="idClient")
	@JsonIgnoreProperties({"reservations","messages"})
	private Cliente client;
	
	
	private String score = null;

	
	//===  Getters and Setters ======//
	
	

	public int getIdReservation() {
		return idReservation;
	}	

	public String getScore() {
		return score;
	}





	public void setScore(String score) {
		this.score = score;
	}





	public Disfraz getCostume() {
		return costume;
	}

	public void setCostume(Disfraz costume) {
		this.costume = costume;
	}





	public Cliente getClient() {
		return client;
	}





	public void setClient(Cliente client) {
		this.client = client;
	}





	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDevolutionDate() {
		return devolutionDate;
	}

	public void setDevolutionDate(String devolutionDate) {
		this.devolutionDate = devolutionDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
