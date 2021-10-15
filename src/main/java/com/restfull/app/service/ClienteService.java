package com.restfull.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfull.app.entity.Cliente;
import com.restfull.app.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired private ClienteRepository clienteRepository;
	
	public List<Cliente> getClientes(){
		return clienteRepository.findAll();
	}
	
	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Optional<Cliente> getCliente(int id){
		return clienteRepository.findById(id);
	}
	
	public void deleteCliente(int id) {
		clienteRepository.deleteById(id);
	}

}
