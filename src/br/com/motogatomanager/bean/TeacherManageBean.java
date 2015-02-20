package br.com.motogatomanager.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.motogatomanager.DB.StaticDB;
import br.com.motogatomanager.modelo.Teacher;

@ManagedBean
public class TeacherManageBean {
	private Teacher teacher;
	private String pass;
	private String passConfirm;
	
	@PostConstruct
	public void init () {
		
	}
	
	public String save () {
		teacher.setObjectId("objTeste");
		StaticDB.TEACHERS.add(teacher);
		return "teachers";
	}
	
	public String back () {
		return "teachers";
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPassConfirm() {
		return passConfirm;
	}

	public void setPassConfirm(String passConfirm) {
		this.passConfirm = passConfirm;
	}

}
