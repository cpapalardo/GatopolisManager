package br.com.farofa.gm.webservice;

import java.io.Serializable;
import java.util.List;

import org.json.JSONObject;

import br.com.farofa.gm.dao.GenericDAO;
import br.com.farofa.gm.dao.GenericDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.JsonBehaviour;

@SuppressWarnings("unchecked")
public class GenericWSImpl<T extends JsonBehaviour, PK extends Serializable> implements GenericWS<T, PK> {
	
	private Class<T> clazz;
	
	public GenericWSImpl(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public String save(String json) {
		String result = null;
		GenericDAO<T,PK> dao = new GenericDAOImpl<T,PK>(clazz, DataBaseManager.getEntityManager());
		try {
			T entity = (T) Class.forName(clazz.getName()).newInstance();
			entity.setJson(json);
			dao.save(entity);
			result = getIdentifier(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
		return result;
	}

	@Override
	public void update(String json) {
		GenericDAO<T,PK> dao = new GenericDAOImpl<T, PK>(clazz, DataBaseManager.getEntityManager());
		try {
			T entity = (T) Class.forName(clazz.getName()).newInstance();
			entity.setJson(json);
			dao.update(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
	}

	@Override
	public void delete(String json) {
		GenericDAO<T,PK> dao = new GenericDAOImpl<T, PK>(clazz, DataBaseManager.getEntityManager());
		try {
			T entity = (T) Class.forName(clazz.getName()).newInstance();
			entity.setJson(json);
			dao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
	}

	@Override
	public String findById(PK pk) {
		GenericDAO<T,PK> dao = new GenericDAOImpl<T, PK>(clazz, DataBaseManager.getEntityManager());
		String result = null;
		try {
			T t = dao.findById(pk);
			if (t != null) 
				result = t.getJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
		return result;
	}

	@Override
	public String findByInep(String inep) {
		GenericDAO<T,PK> dao = new GenericDAOImpl<T, PK>(clazz, DataBaseManager.getEntityManager());
		JSONObject result = new JSONObject();
		try {
			List<T> list = dao.findByInep(inep);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String key = clazz.getSimpleName() + "-" + i;
					String json = list.get(i).getJson();
					JSONObject jsonObj = new JSONObject(json);
					result.put(key, jsonObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
		return result.toString();
	}

	@Override
	public String findAll() {
		GenericDAO<T,PK> dao = new GenericDAOImpl<T, PK>(clazz, DataBaseManager.getEntityManager());
		JSONObject result = new JSONObject();
		try {
			List<T> list = dao.findAll();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String key = clazz.getSimpleName() + "-" + i;
					String json = list.get(i).getJson();
					JSONObject jsonObj = new JSONObject(json);
					result.put(key, jsonObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
		return result.toString();
	}
	
	private String getIdentifier(Object entity) {
		Object identifier = DataBaseManager.getFactory().getPersistenceUnitUtil().getIdentifier(entity);
		if (identifier instanceof JsonBehaviour) {
			return getIdentifier (entity);
		} else {
			return String.valueOf(identifier);
		}
	}

}
