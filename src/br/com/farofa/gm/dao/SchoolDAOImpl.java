package br.com.farofa.gm.dao;

import javax.inject.Named;
import javax.persistence.Query;

import br.com.farofa.gm.model.School;

@Named
public class SchoolDAOImpl extends GenericDAOImpl<School, String> implements SchoolDAO {
	
	@Override
	public School findBySyncCode(String syncCode) {
		if (!manager.getTransaction().isActive())
			manager.getTransaction().begin();
		
		String jpql = "select s from School s where s.sync_code = :sync_code";
		Query query = manager.createQuery(jpql);
		query.setParameter("sync_code", syncCode);
		
		School school = null;
		if (query.getResultList().size() > 0)
			school = (School) query.getResultList().get(0);
		
		manager.getTransaction().commit();
		
		return school;
	}
	
	@Override
	public School findByName(String name) {
		if (!manager.getTransaction().isActive())
			manager.getTransaction().begin();
		
		String jpql = "select s from School s where s.schoolData.name = :name";
		Query query = manager.createQuery(jpql);
		query.setParameter("name", name);
		
		School school = null;
		if (query.getResultList().size() > 0)
			school = (School) query.getResultList().get(0);
		
		manager.getTransaction().commit();
		
		return school;
	}
}
