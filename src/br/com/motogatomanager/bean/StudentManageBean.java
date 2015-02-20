package br.com.motogatomanager.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.motogatomanager.modelo.Student;

@ManagedBean
public class StudentManageBean {
	private Student student;
	
	@PostConstruct
	public void init () {
		
	}
	
	public String save () {
		student.setObjectId("objTeste");
		return "students";
	}
	
	public String back () {
		return "students";
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
