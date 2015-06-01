package br.com.farofa.gm.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.farofa.gm.model.SchoolData;

public class SchoolDataDAOImpl extends GenericDAOImpl<SchoolData, String> implements SchoolDataDAO {

	public SchoolDataDAOImpl(EntityManager manager) {
		super(manager);
	}

	/*@Override
	public List<SchoolData> findByInep(String inep) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
