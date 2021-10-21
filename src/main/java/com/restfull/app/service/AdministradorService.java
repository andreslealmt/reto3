package com.restfull.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfull.app.entity.Administrador;
import com.restfull.app.repository.AdministradorRepository;

@Service
public class AdministradorService {

	@Autowired private AdministradorRepository administradorRepository;
	
	public List<Administrador> getAdministradores(){
		return administradorRepository.findAll();
	}
	
	public Administrador saveAdministrador(Administrador administrador) {
		return administradorRepository.save(administrador);
	}
	
	public Optional<Administrador> getAdministrador(int id){
		return administradorRepository.findById(id);
	}
	
	public Boolean deleteAdministrador(int id) {
		if(!administradorRepository.findById(id).isPresent()) {
			return false;
		}
		administradorRepository.deleteById(id);
		return true;
	}
	
	
	
}
