package com.restfull.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfull.app.entity.Cliente;
import com.restfull.app.repository.ClienteRepository;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Service
public class ClienteService {
	
	@Autowired private ClienteRepository clienteRepository;
	
	public List<Cliente> getClientes(){
		return clienteRepository.findAll();
	}
	
	public Cliente saveCliente(Cliente cliente) {
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		String hash = argon2.hash(1, 1024, 1, cliente.getPassword());
		cliente.setPassword(hash);
		return clienteRepository.save(cliente);
	}
	
	public Cliente saveClienteUpdate(Cliente cliente) {		
		return clienteRepository.save(cliente);
	}
	
	public Optional<Cliente> getCliente(int id){
		return clienteRepository.findById(id);
	}
	
	public void deleteCliente(int id) {
		clienteRepository.deleteById(id);
	}

	public Optional<Cliente> obtenerClienteCredenciales(Cliente cliente) {
		Optional<Cliente> obtenerCliente = clienteRepository.findByEmail(cliente.getEmail());
		if(obtenerCliente.isEmpty()) {
			return null;
		}
		
		String passwordHashed = obtenerCliente.get().getPassword();
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		
		if(argon2.verify(passwordHashed, cliente.getPassword())) {
			return obtenerCliente;
		}
		
		return null;
	}
	
	
	
	
	
	
	
}
