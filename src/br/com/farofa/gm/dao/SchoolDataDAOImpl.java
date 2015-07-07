package br.com.farofa.gm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.farofa.gm.model.SchoolData;

public class SchoolDataDAOImpl extends GenericDAOImpl<SchoolData, String> implements SchoolDataDAO {

	public SchoolDataDAOImpl(EntityManager manager) {
		super(SchoolData.class, manager);
	}

}
