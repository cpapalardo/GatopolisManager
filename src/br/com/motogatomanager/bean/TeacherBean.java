package br.com.motogatomanager.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.motogatomanager.db.BancoLocal;
import br.com.motogatomanager.modelo.Teacher;

@ManagedBean
public class TeacherBean {
	
	private Teacher teacher;
	private List<Teacher> teachers = new ArrayList<Teacher>();
	
	
	@PostConstruct
	public void init () {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put ("teacher", null);
		teachers = BancoLocal.TEACHERS;
	}
	
	public String create () {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put ("teacher", new Teacher ());
		return "teacherManage";
	}

	public String edit (Teacher teacher) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put ("teacher", teacher);
		return "teacherManage";
	}
	
	public String addStudents (Teacher teacher) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put ("teacher", teacher);
		return "import";
	}
	
	public String back () {
		return "/home";
	}
	
	
	public String students () {
		return "students";
	}
	
	public String groups () {
		return "groups";
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
