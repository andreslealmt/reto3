package com.restfull.app.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restfull.app.entity.Cliente;
import com.restfull.app.service.ClienteService;
import com.restfull.app.utils.JWTUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AuthController {

	@Autowired ClienteService clienteService;
	
	@Autowired JWTUtil jwtUtil;
	
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody Cliente cliente) {
		Optional<Cliente> clienteLogeado = clienteService.obtenerClienteCredenciales(cliente);
		System.out.println(clienteLogeado);
		Map<String, Object> clienteMap = new HashMap<String, Object>();
		if(clienteLogeado != null) {
			String tokenJwt = jwtUtil.create(String.valueOf(clienteLogeado.get().getIdClient()), clienteLogeado.get().getEmail());
			clienteMap.put("token", tokenJwt);
			clienteMap.put("usuario", clienteLogeado.get());
			return clienteMap;
		}
		
		
		clienteMap.put("usuario", null);
		return clienteMap;
	}
	
}























