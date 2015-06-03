package br.com.farofa.gm.webservice;

import br.com.farofa.gm.model.Teacher;

public class TeacherWS extends GenericWSImpl<Teacher, Integer> {
	public TeacherWS() {
		super(Teacher.class);
	}
}
