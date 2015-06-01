package br.com.farofa.gm.webservice;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.json.JSONObject;

import br.com.farofa.gm.dao.GenericDAO;
import br.com.farofa.gm.dao.GenericDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.JsonBehaviour;

@SuppressWarnings("unchecked")
public class GenericWebImpl<T extends JsonBehaviour, PkType extends Serializable> implements GenericWeb<T, PkType> {
	
	@Override
	public void save(String json) {
		GenericDAO<T,PkType> dao = new GenericDAOImpl<T,PkType>(DataBaseManager.getEntityManager());
		try {
			T entity = (T) Class.forName(getTypeClass().getName()).newInstance();
			JSONObject jsonObj = new JSONObject(json);
			entity.setJson(jsonObj);
			dao.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
	}

	@Override
	public void update(String json) {
		GenericDAO<T,PkType> dao = new GenericDAOImpl<T, PkType>(DataBaseManager.getEntityManager());
		try {
			T entity = (T) Class.forName(getTypeClass().getName()).newInstance();
			JSONObject jsonObj = new JSONObject(json);
			entity.setJson(jsonObj);
			dao.update(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
	}

	@Override
	public void delete(String json) {
		GenericDAO<T,PkType> dao = new GenericDAOImpl<T, PkType>(DataBaseManager.getEntityManager());
		try {
			T entity = (T) Class.forName(getTypeClass().getName()).newInstance();
			JSONObject jsonObj = new JSONObject(json);
			entity.setJson(jsonObj);
			dao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
	}

	@Override
	public String findById(PkType pk) {
		GenericDAO<T,PkType> dao = new GenericDAOImpl<T, PkType>(DataBaseManager.getEntityManager());
		String result = null;
		try {
			T t = dao.findById(pk);
			JSONObject jsonObj = t.getJson();
			result = jsonObj.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
		return result;
	}

	@Override
	public String findByInep(String inep) {
		GenericDAO<T,PkType> dao = new GenericDAOImpl<T, PkType>(DataBaseManager.getEntityManager());
		String result = null;
		try {
			List<T> list = dao.findByInep(inep);
			JSONObject jsonObj = new JSONObject();
			for (int i = 0; i < list.size(); i++) {
				jsonObj.put(getTypeClass().getSimpleName() + i, list.get(i).getJson());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
		return result;
	}

	@Override
	public String findAll() {
		GenericDAO<T,PkType> dao = new GenericDAOImpl<T, PkType>(DataBaseManager.getEntityManager());
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		DataBaseManager.close();
		return null;
	}
	
	private Class<T> getTypeClass() {
		final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();  
		return (Class<T>) (type).getActualTypeArguments()[0];
	}

}
