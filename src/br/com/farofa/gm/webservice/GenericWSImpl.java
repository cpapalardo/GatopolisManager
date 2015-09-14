package br.com.farofa.gm.webservice;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.json.JSONObject;

import br.com.farofa.gm.dao.GenericDAO;
import br.com.farofa.gm.database.DatabaseManager;
import br.com.farofa.gm.model.JsonBehaviour;

public class GenericWSImpl<T extends JsonBehaviour, PK extends Serializable> implements GenericWS<T, PK> {
	private static final String DAOS_PACKAGE = "br.com.farofa.gm.dao.";
	private static final String DAOS_IMPLEMENTATION = "DAOImpl";
	
	private GenericDAO<T,PK> dao;
	private Class<T> entityClass;
	
	@Override
	public String save(String json) {
		System.out.println(json);
		String result = null;
		try {
			dao = getNewDAOInstance();
			T entity = getEntityClass().getDeclaredConstructor(String.class).newInstance(json);
			dao.save(entity);
			Object id = DatabaseManager.getFactory().getPersistenceUnitUtil().getIdentifier(entity);
			result = String.valueOf(id);
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}

	@Override
	public String update(String json) {
		String result = null;
		try {
			dao = getNewDAOInstance();
			T entity = getEntityClass().getDeclaredConstructor(String.class).newInstance(json);
			dao.update(entity);
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}

	@Override
	public String findById(PK pk) {
		String result = null;
		try {
			dao = getNewDAOInstance();
			T t = dao.findById(pk);
			if (t != null) 
				result = t.getJson();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}

	@Override
	public String findByInep(String inep) {
		String result = null;
		JSONObject jsonObj = new JSONObject();
		try {
			dao = getNewDAOInstance();
			List<T> list = dao.findByInep(inep);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String key = getEntityClass().getSimpleName() + "-" + i;
					String json = list.get(i).getJson();
					JSONObject tmp = new JSONObject(json);
					jsonObj.put(key, tmp);
				}
			}
			result = jsonObj.toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}

	@Override
	public String findAll() {
		String result = null;
		JSONObject jsonObj = new JSONObject();
		try {
			dao = getNewDAOInstance();
			List<T> list = dao.findAll();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String key = getEntityClass().getSimpleName() + "-" + i;
					String json = list.get(i).getJson();
					JSONObject tmp = new JSONObject(json);
					jsonObj.put(key, tmp);
				}
			}
			result = jsonObj.toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private GenericDAO<T, PK> getNewDAOInstance () throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		dao = (GenericDAO<T, PK>) Class.forName(DAOS_PACKAGE + getEntityClass().getSimpleName() + DAOS_IMPLEMENTATION).newInstance();
		dao.setEntityManager(DatabaseManager.getEntityManager());
		return dao;
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getEntityClass () {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return entityClass;
	}
}
