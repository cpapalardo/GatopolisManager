package br.com.motogatomanager.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.motogatomanager.DB.StaticDB;
import br.com.motogatomanager.modelo.Teacher;

@ManagedBean
public class TeacherBean {
	
	private Teacher teacher;
	private List<Teacher> teachers = new ArrayList<Teacher>();
	
	
	@PostConstruct
	public void init () {
		teachers = StaticDB.TEACHERS;
	}
	
	public String create () {
		return "teacherManage";
	}

	public String edit () {
		return "teacherManage";
	}
	
	public String addStudents () {
		return "students";
	}
	
	public String back () {
		return "/home";
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}



}
