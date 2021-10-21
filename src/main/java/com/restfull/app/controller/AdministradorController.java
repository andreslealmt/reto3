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

import com.restfull.app.entity.Administrador;
import com.restfull.app.service.AdministradorService;

@RestController
@RequestMapping("/api/Admin/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AdministradorController {
	
	@Autowired AdministradorService administradorService;
	
	@GetMapping("all")
	public List<Administrador> getAll(){
		return administradorService.getAdministradores();
	}
	
	@GetMapping("{id}")
	public Optional<Administrador> getOne(@PathVariable int id){
		return administradorService.getAdministrador(id);
	}
	
	@PostMapping("save")
	@ResponseStatus(HttpStatus.CREATED)
	public Administrador save(@RequestBody Administrador administrador) {
		return administradorService.saveAdministrador(administrador);
	}
	
	@PutMapping("update")
	public ResponseEntity<?> update(@RequestBody Administrador administrador){
		Optional<Administrador> newAdministrador = administradorService.getAdministrador(administrador.getId());
		if(!newAdministrador.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		newAdministrador.get().setNombre(administrador.getNombre());
		newAdministrador.get().setCorreo(administrador.getCorreo());
		newAdministrador.get().setPassword(administrador.getPassword());
		return ResponseEntity.status(201).body(administradorService.saveAdministrador(newAdministrador.get()));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		if(administradorService.deleteAdministrador(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	

}
