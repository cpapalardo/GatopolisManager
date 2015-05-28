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
		} finally {
			manager.close();
		}
	}

	public void update(T entity) {
		try {
			manager.getTransaction().begin();
			manager.merge(entity);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}
	
	@Override
	public void delete(T entity) {
		manager.remove(entity);
		manager.close();
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
		} finally {
			manager.close();
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
		} finally {
			manager.close();
		}
		return result;
	}

	@Override
	public List<T> findByInep(int inep) {
		Query query = manager.createNamedQuery(getTypeClass().getSimpleName() + ".findByInepCode");
		query.setParameter("inep", inep);
		List<T> result = query.getResultList();
		manager.close();
		return result;
	}
	
	private Class<T> getTypeClass() {
		final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();  
		return (Class<T>) (type).getActualTypeArguments()[0];
	}
}
