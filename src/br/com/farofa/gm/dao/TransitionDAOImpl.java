package br.com.farofa.gm.dao;

import javax.persistence.EntityManager;

import br.com.farofa.gm.model.Transition;

public class TransitionDAOImpl extends GenericDAOImpl<Transition, Integer> implements TransitionDAO {

	public TransitionDAOImpl(EntityManager manager) {
		super(manager);
	}

}
