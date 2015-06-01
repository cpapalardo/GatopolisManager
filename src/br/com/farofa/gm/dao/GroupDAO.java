package br.com.farofa.gm.dao;

import java.util.List;

import br.com.farofa.gm.model.Group;
import br.com.farofa.gm.model.Teacher;

public interface GroupDAO extends GenericDAO<Group, Integer> {
	
	public List<Group> findByTeacher(Teacher teacher);
	public Group findByNameAndSerieAndPeriodAndInep(String name, String serie, Character period, String inep);
}
