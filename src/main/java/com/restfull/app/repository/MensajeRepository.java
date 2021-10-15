package com.restfull.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restfull.app.entity.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer>{

}
