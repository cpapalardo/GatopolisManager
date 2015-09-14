package br.com.farofa.gm.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Named
public class GenericDAOImpl<T extends Serializable, PK extends Serializable> implements GenericDAO<T, PK> {
	@Inject
	protected EntityManager manager;
	private Class<T> entityClass;
	
	@Override
	public void save(T entity) {
		try {
			if (!manager.getTransaction().isActive())
				manager.getTransaction().begin();
			manager.persist(entity);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//manager.getTransaction().rollback();
		}
	}
	
	@Override
	public void update(T entity) {
		try {
			if (!manager.getTransaction().isActive())
				manager.getTransaction().begin();
			manager.merge(entity);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//manager.getTransaction().rollback();
		}
	}
	
	@Override
	public void delete(T entity) {
		try{
			if (!manager.getTransaction().isActive())
				manager.getTransaction().begin();
			Object pk = null;//DataBaseManager.getFactory().getPersistenceUnitUtil().getIdentifier(entity);
			entity = (T) manager.find(getEntityClass(), pk);
			manager.remove(entity);
			manager.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
			//manager.getTransaction().rollback();
		}
	}
	
	@Override
	public T findById(PK pk) {
		T result = null;
		try {
			if (!manager.getTransaction().isActive())
				manager.getTransaction().begin();
			result = (T) manager.find(getEntityClass(), pk);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//manager.getTransaction().rollback();
		}
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> result = null;
		try {
			if (!manager.getTransaction().isActive())
				manager.getTransaction().begin();
			result = manager.createQuery(("FROM " + getEntityClass().getSimpleName())).getResultList();
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			//manager.getTransaction().rollback();
			throw e;
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByInep(String inep) {
		List<T> result = null;
		if (!manager.getTransaction().isActive())
			manager.getTransaction().begin();
		
		Query query = manager.createNamedQuery(getEntityClass().getSimpleName() + ".findByInepCode");
		query.setParameter("inep", inep);
		result = query.getResultList();
		
		manager.getTransaction().commit();
			
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getEntityClass () {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return entityClass;
	}
	
	public void setEntityManager (EntityManager manager) {
		this.manager = manager;
	}
}
