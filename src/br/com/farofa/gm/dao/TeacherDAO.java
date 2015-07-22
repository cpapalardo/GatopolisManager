package br.com.farofa.gm.dao;

import javax.inject.Named;

import br.com.farofa.gm.model.Teacher;

@Named
public interface TeacherDAO extends GenericDAO<Teacher, Integer> {
	public Teacher findByNameAndInep(String name, String inep);
}
