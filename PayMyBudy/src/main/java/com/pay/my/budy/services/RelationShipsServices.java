package com.pay.my.budy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pay.my.budy.model.Relationships;
import com.pay.my.budy.repository.RelationShipsRepository;

public class RelationShipsServices {

	@Autowired
	RelationShipsRepository relationShipsRepository;

	public List<Relationships> getRelationShips() {

		return relationShipsRepository.findAll();

	}

}
