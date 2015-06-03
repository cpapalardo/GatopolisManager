package br.com.farofa.gm.dao;

import javax.persistence.EntityManager;

import br.com.farofa.gm.model.ReportStudent;

public class ReportStudentDAOImpl extends GenericDAOImpl<ReportStudent, Integer> implements ReportStudentDAO {

	public ReportStudentDAOImpl(EntityManager manager) {
		super(ReportStudent.class, manager);
	}

}
