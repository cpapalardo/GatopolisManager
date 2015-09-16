package br.com.farofa.gm.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

import br.com.farofa.gm.model.Room;
import br.com.farofa.gm.model.Teacher;

@Named
@SuppressWarnings("unchecked")
public class RoomDAOImpl extends GenericDAOImpl<Room, Integer> implements RoomDAO {

	@Override
	public List<Room> findByInep(String inep) {
		List<Room> result = null;
		try {
			if (!manager.getTransaction().isActive())
				manager.getTransaction().begin();
			
			Query query = manager.createNamedQuery("Room.findByInepCode");
			query.setParameter("inep", inep);
			result = query.getResultList();
			
			for (Room room : result) {
				Query subQ = manager.createQuery("select s.id from Student s where s.room.id = :id");
				subQ.setParameter("id", room.getId());
				int qtdeAlunos = subQ.getResultList().size();
				room.setQtdeAlunos(qtdeAlunos);
			}
			
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			throw e;
		}
		
		return result;
	}
	
	@Override
	public List<Room> findByTeacher(Teacher teacher) {
		List<Room> result = null;
		try {
			if (!manager.getTransaction().isActive())
				manager.getTransaction().begin();
			
			String jpql = "select g from Room g where g.teacher.id = :id";
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
	
	@Override
	public Room findByNameAndSerieAndPeriodAndInep(String name, String serie, Character term, String inep) {
		Room room = null;
		try {
			if (!manager.getTransaction().isActive())
				manager.getTransaction().begin();
			
			String jpql = "select g from Room g where g.name = :name and g.serie = :serie and g.term = :term and g.teacher.school.schoolData.inep = :inep";
			Query query = manager.createQuery(jpql);
			query.setParameter("name", name);
			query.setParameter("serie", serie);
			query.setParameter("term", term);
			query.setParameter("inep", inep);
			
			if (query.getResultList().size() > 0)
				room = (Room) query.getResultList().get(0);
			
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			throw e;
		}
		
		return room;
	}
	
}
