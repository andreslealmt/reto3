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

import com.restfull.app.entity.CalificacionReserva;
import com.restfull.app.service.CaReservaService;

@RestController
@RequestMapping("/api/Score/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CaReservaController {

	@Autowired CaReservaService caReservaService;
	
	@GetMapping("all")
	public List<CalificacionReserva> getAll() {
		return caReservaService.getCaReservas();
	}
	
	@PostMapping("save")
	@ResponseStatus(HttpStatus.CREATED)
	public CalificacionReserva save(@RequestBody CalificacionReserva calificacionReserva) {
		return caReservaService.saveCaReserva(calificacionReserva);
	}
	
	@GetMapping("{id}")
	public Optional<CalificacionReserva> get(@PathVariable int id){		
		return caReservaService.getCaReserva(id);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody CalificacionReserva calificacionReserva){
		Optional<CalificacionReserva> newCaReserva = caReservaService.getCaReserva(id);
		if(!newCaReserva.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		newCaReserva.get().setCalificacion(calificacionReserva.getCalificacion());
		newCaReserva.get().setMensaje(calificacionReserva.getMensaje());	
		return ResponseEntity.status(HttpStatus.CREATED).body(caReservaService.saveCaReserva(newCaReserva.get()));		
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		if(!caReservaService.getCaReserva(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		caReservaService.deleteCaReserva(id);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	
	
	
	
}
