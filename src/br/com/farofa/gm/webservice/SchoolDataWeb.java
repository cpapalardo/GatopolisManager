package br.com.farofa.gm.webservice;

import java.util.List;

import org.json.JSONObject;

import br.com.farofa.gm.dao.SchoolDataDAO;
import br.com.farofa.gm.dao.SchoolDataDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.SchoolData;

public class SchoolDataWeb {
	
	public void save(String json) {
		SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		try {
			SchoolData sd = new SchoolData();
			sd.setJson(json);
			dao.save(sd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
	}

	
	public void update(String json) {
		SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		try {
			SchoolData sd = new SchoolData();
			sd.setJson(json);
			dao.update(sd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
	}

	
	public void delete(String json) {
		SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		try {
			SchoolData sd = new SchoolData();
			sd.setJson(json);
			dao.delete(sd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
	}

	
	public String findById(String pk) {
		SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		String result = null;
		try {
			SchoolData sd = dao.findById(pk);
			if (sd != null){
				result = sd.getJson();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
		return result;
	}

	
	public String findByInep(String inep) {
		SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		JSONObject result = new JSONObject();
		try {
			List<SchoolData> list = dao.findByInep(inep);
			if (list != null){
				for (int i = 0; i < list.size(); i++) {
					String key = getClass().getSimpleName() + "-" + i;
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

	
	public String findAll() {
		SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		JSONObject result = new JSONObject();
		try {
			List<SchoolData> list = dao.findAll();
			if (list != null){
				for (int i = 0; i < list.size(); i++){
					String key = getClass().getSimpleName() + "-" + i;
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
	
}
