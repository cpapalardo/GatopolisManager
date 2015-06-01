package br.com.farofa.gm.dao;

import br.com.farofa.gm.model.Teacher;

public interface TeacherDAO extends GenericDAO<Teacher, Integer> {
	public Teacher findByNameAndInep(String name, String inep);
}
