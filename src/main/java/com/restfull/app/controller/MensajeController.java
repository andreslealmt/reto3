package com.restfull.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.restfull.app.entity.Mensaje;
import com.restfull.app.service.MensajeService;

@RestController
@RequestMapping("/api/Message/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class MensajeController {
	
	@Autowired MensajeService mensajeService;
	
	@GetMapping("all")
	public List<Mensaje> getMensajes(){
		return mensajeService.getMensajes();
	}
	
	@PostMapping("save")
	@ResponseStatus(HttpStatus.CREATED)
	public Mensaje saveMensaje(@RequestBody Mensaje mensaje) {
		return mensajeService.saveMensaje(mensaje);
	}
	
	@GetMapping("{id}")
	public Optional<Mensaje> getMensaje(@PathVariable int id){
		return mensajeService.getMensaje(id);
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updateMensaje(@RequestBody Mensaje mensaje){
		Optional<Mensaje> newMensaje = mensajeService.getMensaje(mensaje.getIdMessage());
		if(!newMensaje.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		newMensaje.get().setMessageText(mensaje.getMessageText());
		return ResponseEntity.status(HttpStatus.CREATED).body(mensajeService.saveMensaje(newMensaje.get()));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Mensaje> deleteMensaje(@PathVariable int id){
		if(!mensajeService.getMensaje(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		mensajeService.deleteMensaje(id);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	
	
	

}
