package br.com.farofa.gm.dao;

import java.util.List;

import javax.inject.Named;

import br.com.farofa.gm.model.Student;
import br.com.farofa.gm.model.Teacher;

@Named
public interface StudentDAO extends GenericDAO<Student, Integer> {
	public List<Student> findByTeacher(Teacher teacher);
}
