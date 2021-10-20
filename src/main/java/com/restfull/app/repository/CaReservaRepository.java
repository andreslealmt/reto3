package com.restfull.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restfull.app.entity.CalificacionReserva;

@Repository
public interface CaReservaRepository extends JpaRepository<CalificacionReserva, Integer>{

}
