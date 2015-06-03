package br.com.farofa.gm.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.farofa.gm.manager.DataBaseManager;

@SuppressWarnings("unchecked")
public class GenericDAOImpl<T extends Serializable, PK extends Serializable> implements GenericDAO<T, PK> {
	private Class<T> clazz;
	protected EntityManager manager;
	
	public GenericDAOImpl(Class<T> clazz, EntityManager manager) {
		this.clazz = clazz;
		this.manager = manager;
	}
	
	@Override
	public void save(T entity) {
		try {
			manager.getTransaction().begin();
			manager.persist(entity);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	}
	
	@Override
	public void update(T entity) {
		try {
			manager.getTransaction().begin();
			manager.merge(entity);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	}
	
	@Override
	public void delete(T entity) {
		try{
			manager.getTransaction().begin();
			Object pk = DataBaseManager.getFactory().getPersistenceUnitUtil().getIdentifier(entity);
			entity = (T) manager.find(clazz, pk);
			manager.remove(entity);
			manager.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	}
	
	@Override
	public T findById(PK pk) {
		T result = null;
		try {
			manager.getTransaction().begin();
			result = (T) manager.find(clazz, pk);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
		return result;
	}
	
	@Override
	public List<T> findAll() {
		List<T> result = null;
		try {
			manager.getTransaction().begin();
			result = manager.createQuery(("FROM " + clazz.getName())).getResultList();
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
		return result;
	}

	@Override
	public List<T> findByInep(String inep) {
		List<T> result = null;
		
		Query query = manager.createNamedQuery(clazz.getSimpleName() + ".findByInepCode");
		query.setParameter("inep", inep);
		result = query.getResultList();
			
		return result;
	}
	
}
