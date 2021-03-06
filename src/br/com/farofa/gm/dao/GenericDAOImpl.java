package br.com.farofa.gm.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

@Named
public class GenericDAOImpl<T extends Serializable, PK extends Serializable> implements GenericDAO<T, PK> {
	@Inject
	protected EntityManager manager;
	private Class<T> entityClass;
	
	@Override
	public void save(T entity) {
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(entity);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				manager.getTransaction().rollback();
			} catch (Exception e2) {
				
			}
			throw e;
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
			try {
				manager.getTransaction().rollback();
			} catch (Exception e2) {
				
			}
			throw e;
		}
	}
	
	/*@Override
	public void delete(T entity) {
		try{
			manager.getTransaction().begin();
			Object pk = null;//DataBaseManager.getFactory().getPersistenceUnitUtil().getIdentifier(entity);
			entity = (T) manager.find(getEntityClass(), pk);
			manager.remove(entity);
			manager.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;
		}
	}*/
	
	@Override
	public T findById(PK pk) {
		T result = null;
		try {
			result = (T) manager.find(getEntityClass(), pk);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> result = null;
		try {
			result = manager.createQuery(("FROM " + getEntityClass().getSimpleName())).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByInep(String inep) {
		List<T> result = null;
		try {
			Query query = manager.createNamedQuery(getEntityClass().getSimpleName() + ".findByInepCode");
			query.setParameter("inep", inep);
			result = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
			
		return result;
	}
	
	@Override
	public void setEntityManager (EntityManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void close () {
		if (manager != null && manager.isOpen())
			manager.close();
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getEntityClass () {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return entityClass;
	}
	
}
