package br.com.farofa.gm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

import br.com.farofa.gm.model.Teacher;

@Named
public class TeacherDAOImpl extends GenericDAOImpl<Teacher, Integer> implements TeacherDAO {
	
	@Override
	public Teacher findByNameAndInep(String name, String inep) {
		Teacher teacher = null;
		try {			
			String jpql = "select t from Teacher t where t.name = :name and t.school.schoolData.inep = :inep";
			Query query = manager.createQuery(jpql);
			query.setParameter("name", name);
			query.setParameter("inep", inep);
			
			if (query.getResultList().size() > 0)
				teacher = (Teacher) query.getResultList().get(0);
			
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			throw e;
		}
		
		return teacher;
	}

	@Override
	public List<Teacher> findByInepAndDeleted(String inep) {
		List<Teacher> teachers = new ArrayList<Teacher>();
		try{
			if (!manager.getTransaction().isActive())
				manager.getTransaction().begin();
			
			String jpql = "select t from Teacher t where t.school.schoolData.inep = :inep";
			Query query = manager.createQuery(jpql);
			query.setParameter("inep", inep);
			
			teachers = query.getResultList();
			manager.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			throw e;
		}
		return teachers;
	}

}
