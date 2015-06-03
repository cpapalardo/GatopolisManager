package br.com.farofa.gm.dao;

import javax.persistence.EntityManager;

import br.com.farofa.gm.model.GameBird;

public class GameBirdDAOImpl extends GenericDAOImpl<GameBird, Integer> implements GameBirdDAO {

	public GameBirdDAOImpl(EntityManager manager) {
		super(GameBird.class, manager);
	}

}
