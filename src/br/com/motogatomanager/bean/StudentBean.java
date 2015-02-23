package br.com.motogatomanager.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.motogatomanager.db.BancoLocal;
import br.com.motogatomanager.modelo.Student;

@ManagedBean
public class StudentBean {
	
	private List<Student> students = new ArrayList<Student>();
	
	@PostConstruct
	public void init () {
		students = BancoLocal.STUDENTS;
	}
	
	public String create () {
		return "studentManage";
	}
	
	public String edit () {
		return "studentManage";
	}
	
	public String back () {
		return "teachers";
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
