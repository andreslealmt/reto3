package com.restfull.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfull.app.entity.Disfraz;
import com.restfull.app.repository.DisfrazRepository;

@Service
public class DisfrazService {
	
	@Autowired private DisfrazRepository disfrazRepository;
	
	public List<Disfraz> getDisfraces(){
		return disfrazRepository.findAll();
	}
	
	public Disfraz save(Disfraz disfraz) {
		return disfrazRepository.save(disfraz);
	}
	
	public Optional<Disfraz> getDisfraz(int id){
		return disfrazRepository.findById(id);
	}
	
	public void deleteDisfraz(int id) {
		disfrazRepository.deleteById(id);
	}
}
