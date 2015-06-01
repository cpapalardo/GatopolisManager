package br.com.farofa.gm.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@SuppressWarnings("unchecked")
public class GenericDAOImpl<T extends Serializable, PK extends Serializable> implements GenericDAO<T, PK> {
	protected EntityManager manager;

	public GenericDAOImpl(EntityManager manager) {
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
			result = (T) manager.find(getTypeClass(), pk);
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
			result = manager.createQuery(("FROM " + getTypeClass().getName())).getResultList();
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
		
		Query query = manager.createNamedQuery(getTypeClass().getSimpleName() + ".findByInepCode");
		query.setParameter("inep", inep);
		result = query.getResultList();
			
		return result;
	}
	
	private Class<T> getTypeClass() {
		final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();  
		return (Class<T>) (type).getActualTypeArguments()[0];
	}
}
