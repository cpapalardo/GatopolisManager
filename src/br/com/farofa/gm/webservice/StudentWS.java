package br.com.farofa.gm.webservice;

import java.util.List;

import org.json.JSONObject;

import br.com.farofa.gm.dao.StudentDAO;
import br.com.farofa.gm.dao.StudentDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.Student;

public class StudentWS {
	public Integer save(String json) {
		Integer result = null;
		StudentDAO dao = new StudentDAOImpl(DataBaseManager.getEntityManager());
		try {
			Student student = new Student();
			student.setJson(json);
			dao.save(student);
			Object identifier = DataBaseManager.getFactory().getPersistenceUnitUtil().getIdentifier(student);
			result = (Integer) identifier;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
		return result;
	}

	
	public void update(String json) {
		StudentDAO dao = new StudentDAOImpl(DataBaseManager.getEntityManager());
		try {
			Student sd = new Student();
			sd.setJson(json);
			dao.update(sd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
	}

	
	public void delete(String json) {
		StudentDAO dao = new StudentDAOImpl(DataBaseManager.getEntityManager());
		try {
			Student sd = new Student();
			sd.setJson(json);
			dao.delete(sd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
	}

	
	public String findById(Integer pk) {
		StudentDAO dao = new StudentDAOImpl(DataBaseManager.getEntityManager());
		String result = null;
		try {
			Student student = dao.findById(pk);
			if (student != null){
				result = student.getJson();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
		return result;
	}

	
	public String findByInep(String inep) {
		StudentDAO dao = new StudentDAOImpl(DataBaseManager.getEntityManager());
		JSONObject result = new JSONObject();
		try {
			List<Student> list = dao.findByInep(inep);
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
		StudentDAO dao = new StudentDAOImpl(DataBaseManager.getEntityManager());
		JSONObject result = new JSONObject();
		try {
			List<Student> list = dao.findAll();
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
