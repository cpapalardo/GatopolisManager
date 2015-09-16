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
		List<Student> result = null;
		try {
			if (!manager.getTransaction().isActive())
				manager.getTransaction().begin();
			
			String jpql = "select s from Student s where s.room.teacher.id = :id";
			Query query = manager.createQuery(jpql);
			query.setParameter("id", teacher.getId());
			
			result = query.getResultList();
			
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			throw e;
		}
		
		return result;
	}
	
}
