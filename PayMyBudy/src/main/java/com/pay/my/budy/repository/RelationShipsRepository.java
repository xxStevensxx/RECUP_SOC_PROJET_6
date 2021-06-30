package com.pay.my.budy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pay.my.budy.model.Relationships;

@Repository
public interface RelationShipsRepository extends JpaRepository<Relationships, Integer> {
	
	
	


}
