package com.restfull.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfull.app.entity.Reservacion;
import com.restfull.app.repository.ReservacionRepository;

@Service
public class ReservacionService {
	
	@Autowired private ReservacionRepository reservacionRepository;
	
	public List<Reservacion> getReservaciones(){
		return reservacionRepository.findAll();
	}
	
	public Optional<Reservacion> getReservacion(int id){
		return reservacionRepository.findById(id);
	}
	
	public Reservacion saveReservacion(Reservacion reservacion) {
		reservacion.setStartDate(reservacion.getStartDate()+"T00:00:00.000+00:00");
		reservacion.setDevolutionDate(reservacion.getDevolutionDate()+"T00:00:00.000+00:00");
		return reservacionRepository.save(reservacion);
	}
	
	public void deleteReservacion(int id) {
		reservacionRepository.deleteById(id);
	}

}
