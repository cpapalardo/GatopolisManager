package br.com.farofa.gm.dao;

import java.util.List;

import br.com.farofa.gm.model.Room;
import br.com.farofa.gm.model.Teacher;

public interface RoomDAO extends GenericDAO<Room, Integer> {
	
	public List<Room> findByTeacher(Teacher teacher);
	public Room findByNameAndSerieAndPeriodAndInep(String name, String serie, Character period, String inep);
}
