package br.com.farofa.gm.dao;

import javax.persistence.EntityManager;

import br.com.farofa.gm.model.Buildings;

public class BuildingsDAOImpl extends GenericDAOImpl<Buildings, Integer> implements BuildingsDAO {

	public BuildingsDAOImpl(EntityManager manager) {
		super(Buildings.class, manager);
	}

}
