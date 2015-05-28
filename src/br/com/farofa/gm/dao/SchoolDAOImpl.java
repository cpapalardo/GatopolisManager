package br.com.farofa.gm.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.farofa.gm.model.School;

public class SchoolDAOImpl extends GenericDAOImpl<School, Integer> implements SchoolDAO {

	public SchoolDAOImpl(EntityManager manager) {
		super(manager);
	}

	@Override
	public School findBySyncCode(int syncCode) {
		String jpql = "select s from School s where s.sync_code = :sync_code";
		Query query = manager.createQuery(jpql);
		query.setParameter("sync_code", syncCode);
		
		School school = null;
		if (query.getResultList().size() > 0)
			school = (School) query.getResultList().get(0);
		
		return school;
	}
	
	@Override
	public School findByName(String name) {
		String jpql = "select s from School s where s.schoolData.name = :name";
		Query query = manager.createQuery(jpql);
		query.setParameter("name", name);
		
		School school = null;
		if (query.getResultList().size() > 0)
			school = (School) query.getResultList().get(0);
		
		return school;
	}
}
