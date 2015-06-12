package br.com.farofa.gm.webservice;

import br.com.farofa.gm.dao.SchoolDAO;
import br.com.farofa.gm.dao.SchoolDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.School;

public class SchoolWS extends GenericWSImpl<School, String> {
	public SchoolWS() {
		super(School.class);
	}
	
	public String loadSchoolsBySyncCode(String syncCode) {
		String result = null;
		SchoolDAO dao = new SchoolDAOImpl(DataBaseManager.getEntityManager());
		try {
			School school = dao.findBySyncCode(syncCode);
			result = school.getJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
		return result;
	}
}
