package br.com.farofa.gm.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.farofa.gm.model.Teacher;


public class TeacherDAOImpl extends GenericDAOImpl<Teacher, Integer> implements TeacherDAO {
	public TeacherDAOImpl(EntityManager manager) {
		super(Teacher.class, manager);
	}

	@Override
	public Teacher findByNameAndInep(String name, String inep) {
		String jpql = "select t from Teacher t where t.name = :name and t.school.schoolData.inep = :inep";
		Query query = manager.createQuery(jpql);
		query.setParameter("name", name);
		query.setParameter("inep", inep);
		
		Teacher teacher = null;
		if (query.getResultList().size() > 0)
			teacher = (Teacher) query.getResultList().get(0);
		
		return teacher;
	}

}
