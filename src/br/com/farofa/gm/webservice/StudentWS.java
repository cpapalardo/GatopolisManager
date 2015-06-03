package br.com.farofa.gm.webservice;

import br.com.farofa.gm.model.Student;

public class StudentWS extends GenericWSImpl<Student, Integer> {

	public StudentWS() {
		super(Student.class);
	}
	
}
