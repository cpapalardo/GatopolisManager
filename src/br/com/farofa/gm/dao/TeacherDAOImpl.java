package br.com.farofa.gm.dao;

import javax.inject.Named;
import javax.persistence.Query;

import br.com.farofa.gm.model.Teacher;

@Named
public class TeacherDAOImpl extends GenericDAOImpl<Teacher, Integer> implements TeacherDAO {
	
	@Override
	public Teacher findByNameAndInep(String name, String inep) {
		if (!manager.getTransaction().isActive())
			manager.getTransaction().begin();
		
		String jpql = "select t from Teacher t where t.name = :name and t.school.schoolData.inep = :inep";
		Query query = manager.createQuery(jpql);
		query.setParameter("name", name);
		query.setParameter("inep", inep);
		
		Teacher teacher = null;
		if (query.getResultList().size() > 0)
			teacher = (Teacher) query.getResultList().get(0);
		
		manager.getTransaction().commit();
		
		return teacher;
	}

}
