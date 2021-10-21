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

import com.restfull.app.entity.Disfraz;
import com.restfull.app.service.DisfrazService;

@RestController
@RequestMapping("/api/Costume/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class DisfrazController {
	
	@Autowired DisfrazService disfrazService;
	
	@GetMapping("all")
	public List<Disfraz> getDisfraces(){
		return disfrazService.getDisfraces();
	}
	
	@PostMapping("save")
	@ResponseStatus(HttpStatus.CREATED)
	public Disfraz saveDisfraz(@RequestBody Disfraz disfraz) {
		return disfrazService.save(disfraz);
	}
	
	@GetMapping("{id}")
	public Optional<Disfraz> getDisfraz(@PathVariable int id) {
		return disfrazService.getDisfraz(id);
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updateDisfraz(@RequestBody Disfraz disfraz) {
		Optional<Disfraz> newDisfraz = disfrazService.getDisfraz(disfraz.getId());
		if(!newDisfraz.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		newDisfraz.get().setBrand(disfraz.getBrand());
		newDisfraz.get().setDescription(disfraz.getDescription());
		newDisfraz.get().setName(disfraz.getName());
		newDisfraz.get().setYear(disfraz.getYear());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(disfrazService.save(newDisfraz.get()));
		
	}
	
	@DeleteMapping("{id}")
	public void deleteDisfraz(@PathVariable int id){
		if(!disfrazService.getDisfraz(id).isPresent()) {
			//return ResponseEntity.notFound().build();
		}
		
		disfrazService.deleteDisfraz(id);
		//return ResponseEntity.ok().build();
	}

}
