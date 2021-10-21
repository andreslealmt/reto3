package com.restfull.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restfull.app.entity.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer>{

}
