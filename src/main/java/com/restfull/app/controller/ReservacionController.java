package com.restfull.app.controller;

import java.util.List;
import java.util.Map;
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

import com.restfull.app.entity.Reservacion;
import com.restfull.app.service.ReservacionService;

@RestController
@RequestMapping("/api/Reservation/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReservacionController {
	
	@Autowired ReservacionService reservacionService;
	
	@GetMapping("all")
	public List<Reservacion> getReservaciones(){
		return reservacionService.getReservaciones();
	}
	
	@PostMapping("save")
	@ResponseStatus(HttpStatus.CREATED)
	public Reservacion saveReservacion(@RequestBody Reservacion reservacion) {
		return reservacionService.saveReservacion(reservacion);
	}
	
	@GetMapping("{id}")
	public Optional<Reservacion> getReservacion(@PathVariable int id){
		return reservacionService.getReservacion(id);
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updateReservacion(@RequestBody Reservacion reservacion){
		Optional<Reservacion> newReservacion = reservacionService.getReservacion(reservacion.getIdReservation());
		if(!newReservacion.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		newReservacion.get().setStatus(reservacion.getStatus());
		return ResponseEntity.status(HttpStatus.CREATED).body(reservacionService.saveReservacion(newReservacion.get()));
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteReservacion(@PathVariable int id){
		if(!reservacionService.getReservacion(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		reservacionService.deleteReservacion(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("report-dates/{fechaInicio}/{fechaFinal}")
	public List<Reservacion> reportesFecha(@PathVariable String fechaInicio, @PathVariable String fechaFinal) {		
		return reservacionService.reportesFecha(fechaInicio, fechaFinal);
	}
	
	@GetMapping("report-status")
	public Map<String, Integer> reporteStatus(){
		return reservacionService.reportStatus();
	}
	
	@GetMapping("report-clients")
	public List<Object> reporteClientes(){
		return reservacionService.reporteClientes();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
