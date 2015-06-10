package br.com.farofa.gm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.farofa.gm.model.Room;
import br.com.farofa.gm.model.Teacher;


@SuppressWarnings("unchecked")
public class RoomDAOImpl extends GenericDAOImpl<Room, Integer> implements RoomDAO {
	
	public RoomDAOImpl(EntityManager manager) {
		super(Room.class, manager);
	}

	@Override
	public List<Room> findByInep(String inep) {
		Query query = manager.createNamedQuery("Room.findByInepCode");
		query.setParameter("inep", inep);
		List<Room> result = query.getResultList();
		
		for (Room room : result) {
			Query subQ = manager.createQuery("select s.id from Student s where s.room.id = :id");
			subQ.setParameter("id", room.getId());
			int qtdeAlunos = subQ.getResultList().size();
			room.setQtdeAlunos(qtdeAlunos);
		}
		
		return result;
	}
	
	@Override
	public List<Room> findByTeacher(Teacher teacher) {
		String jpql = "select g from Room g where g.teacher.id = :id";
		Query query = manager.createQuery(jpql);
		query.setParameter("id", teacher.getId());
		
		List<Room> result = query.getResultList();
		
		return result;
	}
	
	@Override
	public Room findByNameAndSerieAndPeriodAndInep(String name, String serie, Character period, String inep) {
		String jpql = "select g from Room g where g.name = :name and g.serie = :serie and g.period = :period and g.teacher.school.schoolData.inep = :inep";
		Query query = manager.createQuery(jpql);
		query.setParameter("name", name);
		query.setParameter("serie", serie);
		query.setParameter("period", period);
		query.setParameter("inep", inep);
		
		Room room = null;
		if (query.getResultList().size() > 0)
			room = (Room) query.getResultList().get(0);
		
		return room;
	}
	
}
