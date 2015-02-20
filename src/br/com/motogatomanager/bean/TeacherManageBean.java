package br.com.motogatomanager.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.motogatomanager.DB.StaticDB;
import br.com.motogatomanager.modelo.Teacher;

@ManagedBean
public class TeacherManageBean {
	private Teacher teacher;
	
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

}
