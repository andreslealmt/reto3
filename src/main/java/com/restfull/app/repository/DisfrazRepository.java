package com.restfull.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restfull.app.entity.Disfraz;

@Repository
public interface DisfrazRepository extends JpaRepository<Disfraz, Integer> {

	
}
