package com.restfull.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfull.app.entity.CalificacionReserva;
import com.restfull.app.repository.CaReservaRepository;

@Service
public class CaReservaService {
	
	@Autowired private CaReservaRepository caReservaRepository;
	
	public List<CalificacionReserva> getCaReservas(){
		return caReservaRepository.findAll();
	}
	
	public CalificacionReserva saveCaReserva(CalificacionReserva calificacionReserva) {
		return caReservaRepository.save(calificacionReserva);
	}
	
	public Optional<CalificacionReserva> getCaReserva(int id){
		return caReservaRepository.findById(id);
	}
	
	public void deleteCaReserva(int id) {
		caReservaRepository.deleteById(id);
	}

}
