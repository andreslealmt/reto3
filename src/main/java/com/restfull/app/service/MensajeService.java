package com.restfull.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfull.app.entity.Mensaje;
import com.restfull.app.repository.MensajeRepository;

@Service
public class MensajeService {

	@Autowired private MensajeRepository mensajeRepository;
	
	public List<Mensaje> getMensajes(){
		return mensajeRepository.findAll();
	}
	
	public Mensaje saveMensaje(Mensaje mensaje) {
		return mensajeRepository.save(mensaje);
	}
	
	public Optional<Mensaje> getMensaje(int id){
		return mensajeRepository.findById(id);
	}
	
	public void deleteMensaje(int id) {
		mensajeRepository.deleteById(id);
	}
}
