package br.com.farofa.gm.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.farofa.gm.model.SchoolData;

public class SchoolDataDAOImpl extends GenericDAOImpl<SchoolData, Integer> implements SchoolDataDAO {

	public SchoolDataDAOImpl(EntityManager manager) {
		super(manager);
	}

	@Override
	public List<SchoolData> findByInep() {
		// TODO Auto-generated method stub
		return null;
	}

}
