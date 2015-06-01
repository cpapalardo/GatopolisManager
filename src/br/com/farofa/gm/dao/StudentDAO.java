package br.com.farofa.gm.dao;

import java.util.List;

import br.com.farofa.gm.model.Student;
import br.com.farofa.gm.model.Teacher;

public interface StudentDAO extends GenericDAO<Student, Integer> {
	public List<Student> findByTeacher(Teacher teacher);
}
