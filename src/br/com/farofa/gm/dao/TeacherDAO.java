package br.com.farofa.gm.dao;

import java.util.List;

import javax.inject.Named;

import br.com.farofa.gm.model.Teacher;

@Named
public interface TeacherDAO extends GenericDAO<Teacher, Integer> {
	public Teacher findByNameAndInep(String name, String inep);
	public List<Teacher> findByInepAndDeleted(String inep);
}
