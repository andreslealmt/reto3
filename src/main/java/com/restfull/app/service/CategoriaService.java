package com.restfull.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfull.app.entity.Categoria;
import com.restfull.app.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired private CategoriaRepository categoriaRepository;
	
	public List<Categoria> getCategorias(){
		return categoriaRepository.findAll();
	}
	
	public Categoria saveCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Optional<Categoria> getCategoria(int id){
		return categoriaRepository.findById(id);
	}
	
	public void deleteCategoria(int id) {
		categoriaRepository.deleteById(id);
	}
	
	
	
}
