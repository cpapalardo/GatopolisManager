package br.com.farofa.gm.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

import br.com.farofa.gm.model.Student;
import br.com.farofa.gm.model.Teacher;

@Named
public class StudentDAOImpl extends GenericDAOImpl<Student, Integer> implements StudentDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findByTeacher(Teacher teacher) {
		if (!manager.getTransaction().isActive())
			manager.getTransaction().begin();
		
		String jpql = "select s from Student s where s.room.teacher.id = :id";
		Query query = manager.createQuery(jpql);
		query.setParameter("id", teacher.getId());
		
		List<Student> result = query.getResultList();
		
		manager.getTransaction().commit();
		
		return result;
	}
	
}
