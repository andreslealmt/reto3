package com.restfull.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfull.app.entity.Reservacion;
import com.restfull.app.repository.ReservacionRepository;

/**
*
* @author andres
*/

@Service
public class ReservacionService {
	
	/** injectando bean ReservacionRepository */
	@Autowired private ReservacionRepository reservacionRepository;
	
	/** metodo que lista todas las reservaciones */
	public List<Reservacion> getReservaciones(){
		return reservacionRepository.findAll();
	}
	
	/** 
	 * metodo que obtiene una reservacion por id 
	 * @param id para encontrar la reservacion
	 * */
	public Optional<Reservacion> getReservacion(int id){
		return reservacionRepository.findById(id);
	}
	
	/** 
	 * metodo que guarda una reservacion 
	 * @param reservacion instancia de Reservacion que se almacena en la DB
	 * */
	public Reservacion saveReservacion(Reservacion reservacion) {
		reservacion.setStartDate(reservacion.getStartDate()+"T00:00:00.000+00:00");
		reservacion.setDevolutionDate(reservacion.getDevolutionDate()+"T00:00:00.000+00:00");
		return reservacionRepository.save(reservacion);
	}
	
	/** 
	 * metodo que elimina una reservacion 
	 * @param id para encontrar y eliminar la reservacion
	 * */
	public void deleteReservacion(int id) {
		reservacionRepository.deleteById(id);
	}

}
