package br.com.farofa.gm.webservice;

import br.com.farofa.gm.dao.SchoolDAO;
import br.com.farofa.gm.dao.SchoolDAOImpl;
import br.com.farofa.gm.database.DatabaseManager;
import br.com.farofa.gm.model.School;

public class SchoolWS extends GenericWSImpl<School, String> {
	
	private SchoolDAO dao;
	
	public String findBySyncCode(String syncCode) {
		dao = new SchoolDAOImpl();
		dao.setEntityManager(DatabaseManager.getEntityManager());
		String result = null;
		try {
			School school = dao.findBySyncCode(syncCode);
			result = school.getJson();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
	
	public String findByName (String schoolName) {
		dao = new SchoolDAOImpl();
		dao.setEntityManager(DatabaseManager.getEntityManager());
		String result = null;
		try {
			School school = dao.findByName(schoolName);
			result = school.getJson();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
}
