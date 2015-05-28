package br.com.farofa.gm.dao;

import javax.persistence.EntityManager;

import br.com.farofa.gm.model.ReportTeacher;

public class ReportTeacherDAOImpl extends GenericDAOImpl<ReportTeacher, Integer> implements ReportTeacherDAO {

	public ReportTeacherDAOImpl(EntityManager manager) {
		super(manager);
	}

}
