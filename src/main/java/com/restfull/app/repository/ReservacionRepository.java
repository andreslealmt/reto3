package com.restfull.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restfull.app.entity.Reservacion;

@Repository
public interface ReservacionRepository extends JpaRepository<Reservacion, Integer>{
	
	

}
