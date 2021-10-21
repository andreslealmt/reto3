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

import com.restfull.app.entity.Categoria;
import com.restfull.app.service.CategoriaService;

@RestController
@RequestMapping("/api/Category/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CategoriaController {

	@Autowired CategoriaService categoriaService;
	
	@GetMapping("all")
	public List<Categoria> getCategorias(){
		return categoriaService.getCategorias();
	}
	
	@PostMapping("save")
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria save(@RequestBody Categoria categoria) {
		return categoriaService.saveCategoria(categoria);
	}
	
	@GetMapping("{id}")
	public Optional<Categoria> getCategoria(@PathVariable int id){
		return categoriaService.getCategoria(id);
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updateCategoria(@RequestBody Categoria categoria){
		Optional<Categoria> newCategoria = categoriaService.getCategoria(categoria.getId());
		if(!newCategoria.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		newCategoria.get().setName(categoria.getName());
		newCategoria.get().setDescription(categoria.getDescription());		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.saveCategoria(newCategoria.get()));		
	}
	
	@DeleteMapping("{id}")
	public void deleteCategoria(@PathVariable int id){
		if(!categoriaService.getCategoria(id).isPresent()) {
			//return ResponseEntity.notFound().build();
		}
		categoriaService.deleteCategoria(id);
		//return ResponseEntity.ok().build();
	}
	
	
	
	
}
