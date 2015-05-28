package br.com.farofa.gm.dao;

import javax.persistence.EntityManager;

import br.com.farofa.gm.model.WrittenWord;

public class WrittenWordDAOImpl extends GenericDAOImpl<WrittenWord, Integer> implements WrittenWordDAO {

	public WrittenWordDAOImpl(EntityManager manager) {
		super(manager);
	}

}
