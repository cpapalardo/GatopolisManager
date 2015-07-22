package br.com.farofa.gm.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
public interface GenericDAO<T extends Serializable, PK extends Serializable> {
	public void save(T entity);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public T findById(PK pk);
	
	public List<T> findByInep (String inep);
	
	public List<T> findAll();
	
	public void setEntityManager(EntityManager manager);
}
