package com.restfull.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfull.app.entity.Cliente;
import com.restfull.app.entity.Reservacion;
import com.restfull.app.repository.ClienteRepository;
import com.restfull.app.repository.ReservacionRepository;

/**
*
* @author andres
*/

@Service
public class ReservacionService {
	
	/** injectando bean ReservacionRepository */
	@Autowired private ReservacionRepository reservacionRepository;
	
	/** injectando bean ClienteRepository */
	@Autowired private ClienteRepository clienteRepository;	
	
	
	/** metodo que lista todas las reservaciones */
	public List<Reservacion> getReservaciones(){
		return reservacionRepository.findAll();
	}
	
	/** 
	 * metodo que obtiene una reservacion por id 
	 * @param id para encontrar la reservacion
	 * */
	public Optional<Reservacion> getReservacion(int id){
		return reservacionRepository.findById(id);
	}
	
	/** 
	 * metodo que guarda una reservacion 
	 * @param reservacion instancia de Reservacion que se almacena en la DB
	 * */
	public Reservacion saveReservacion(Reservacion reservacion) {
		reservacion.setStartDate(reservacion.getStartDate()+"T00:00:00.000+00:00");
		reservacion.setDevolutionDate(reservacion.getDevolutionDate()+"T00:00:00.000+00:00");
		return reservacionRepository.save(reservacion);
	}
	
	/** 
	 * metodo que elimina una reservacion 
	 * @param id para encontrar y eliminar la reservacion
	 * */
	public void deleteReservacion(int id) {
		reservacionRepository.deleteById(id);
	}
	
	/** 
	 * metodo que lista reservaciones segun rango de fechas
	 * @param fechaInicial determina la fecha inical del rango
	 * @param fechaFinal determina la fecha final del rango
	 * */
	public List<Reservacion> reportesFecha(String fechaInicial, String fechaFinal){
		
		int anoInicial = Integer.parseInt(fechaInicial.substring(0,4));
		int mesInicial = Integer.parseInt(fechaInicial.substring(5,7));
		int diaInicial = Integer.parseInt(fechaInicial.substring(8,10));
		
		int anoFinal = Integer.parseInt(fechaFinal.substring(0,4));
		int mesFinal = Integer.parseInt(fechaFinal.substring(5,7));
		int diaFinal = Integer.parseInt(fechaFinal.substring(8,10));
		
		List<Reservacion> filtroReservaciones = new ArrayList<Reservacion>();
		List<Reservacion> reservaciones = reservacionRepository.findAll();
		for(Reservacion r : reservaciones) {
			int ano = Integer.parseInt(r.getStartDate().substring(0,4));
			int mes = Integer.parseInt(r.getStartDate().substring(5,7));
			int dia = Integer.parseInt(r.getStartDate().substring(8,10));
			
			if(ano  >= anoInicial && ano <= anoFinal ) {
				if(mes >= mesInicial && mes <= mesFinal) {
					if(dia >= diaInicial && dia <= diaFinal) {
						filtroReservaciones.add(r);
					}
				}
			}
				
			
			//System.out.println( "año"+ano +"mes"+mes+"dia"+dia);
			
		}
		
		//System.out.println(fechaInicial+fechaFinal);
		return filtroReservaciones;
	}
	
	
	/** 
	 * metodo que returna la cantidad de satatus por tipo
	 * */	
	public Map<String, Integer> reportStatus(){
		int completos = 0;
		int cancelados = 0;
		
		List<Reservacion> reservaciones = reservacionRepository.findAll();
		for(Reservacion r : reservaciones) {
			if(r.getStatus().equals("completed")) {
				completos++;
			}
			if(r.getStatus().equals("cancelled")) {
				cancelados++;
			}
		}
		
		//Map<String , Integer> status = new HashMap<String, Integer>();
		LinkedHashMap<String , Integer> status = new LinkedHashMap<>();
		status.put("completed", completos);
		status.put("cancelled", cancelados);		
		
		return status;
	}
	
	
	/** 
	 * metodo que returna la cantidad de clientes con reservas
	 * de estatus "completed"
	 * */	
	public List<Object> reporteClientes(){
		
		
		List<Object> reporteTotal = new ArrayList<Object>();
		
		List<Cliente> clientesOrdenados = ordenarClientesDecendente();
		
		
		
		// guardando en lista para retornar al cliente		
		
		
		for(Cliente c :  clientesOrdenados) {
			Map<String, Object> reporteIndividual = new HashMap<String, Object>();		
		
			reporteIndividual.put("total", c.getReservations().size());
			reporteIndividual.put("client", c);			
			reporteTotal.add(reporteIndividual);			
			
		}		
		return reporteTotal;
	}
	
	
	
	/** 
	 * metodo que retorna una lista de clientes ordenados
	 * segun la cantidad de reservas completadas, en orden decendente
	 * */	
	public List<Cliente> ordenarClientesDecendente() {
		List<Cliente> clientes = clienteRepository.findAll();
		
		Map<Integer, Integer> mayorAMenor = new HashMap<Integer, Integer>();
		
		
		// Almacenando clientes con respetiva cantidad de "completed"
		
		for(Cliente c : clientes) {
			int cuenta = 0;
			for(Reservacion r : c.getReservations()) {
				if(r.getStatus().equals("completed")) {
					cuenta++;
				}
			}
			mayorAMenor.put(c.getIdClient(), cuenta);			
		}
		
		// ordenado por mayor cantidad de copleted
		
		List<Entry<Integer, Integer>> list = new ArrayList<>(mayorAMenor.entrySet());
		list.sort(Entry.comparingByValue());
		
	
		List<Integer> valores = new ArrayList<Integer>();
		List<Cliente> clientesOrdenados = new ArrayList<Cliente>();
		
		// almacenando id's de los clientes ordenados menor a mayor
	    
		for(Entry<Integer, Integer> a : list) {
			valores.add(a.getKey());
		}
		
		// ordenando lista de clientes en orden decendente 
		// segun la cantidad de "completed" en status
		
		for(int i=valores.size()-1; i>=0; i--) {
			System.out.println(valores.get(i));
			for (Cliente c : clientes) {
				if(valores.get(i) == c.getIdClient()) {
					clientesOrdenados.add(c);
				}
			}
			
		}
		
		return clientesOrdenados;
	}
	
	
	
	
	
	
	
	
	

}
