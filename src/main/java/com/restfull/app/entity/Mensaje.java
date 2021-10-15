package com.restfull.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "messages")
public class Mensaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMessage;
	
	@Column(length = 250)
	private String messageText;
	
	@ManyToOne
	@JoinColumn(name="disfraz_id")
	@JsonIgnoreProperties({"messages","reservations"})	
	private Disfraz costume;
	
	@ManyToOne
	@JoinColumn(name="idClient")
	@JsonIgnoreProperties({"messages","reservations"})	
	private Cliente client;
	

	
	
	//===  Getters and Setters ======//
	
	
	

	public int getIdMessage() {
		return idMessage;
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




	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	
	
	
	
	
	

}
