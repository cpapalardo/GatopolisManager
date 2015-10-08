package br.com.farofa.gm.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

import br.com.farofa.gm.model.Room;
import br.com.farofa.gm.model.Student;
import br.com.farofa.gm.model.Teacher;

@Named
public class StudentDAOImpl extends GenericDAOImpl<Student, Integer> implements StudentDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findByTeacher(Teacher teacher) {
		List<Student> result = null;
		try {
			String jpql = "select s from Student s where s.room.teacher.id = :id";
			Query query = manager.createQuery(jpql);
			query.setParameter("id", teacher.getId());
			
			result = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}

	@Override
	public Student findByNameDateRoomInep(String name, Date date, Room room, String inep) {
		Student student = null;
		try{
			if(!manager.getTransaction().isActive())
				manager.getTransaction().begin();
			String jpql = "select s from Student s where s.name = :name and s.birthDate = :date and s.room = :room and s.room.teacher.school.schoolData.inep = :inep";
			Query query = manager.createQuery(jpql);
			
			query.setParameter("name", name);
			query.setParameter("date", date);
			query.setParameter("room", room);
			query.setParameter("inep", inep);
			
			if(!query.getResultList().isEmpty())
				student = (Student)query.getSingleResult();
			
			manager.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			throw e;
		}
		return student;
	}
	
}
