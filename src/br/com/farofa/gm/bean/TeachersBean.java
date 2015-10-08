package br.com.farofa.gm.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.farofa.gm.dao.TeacherDAO;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.Teacher;

@Named
@RequestScoped
public class TeachersBean {
	@Inject
	private TeacherDAO teacherDAO;
	
	private School school;
	private Teacher teacher;
	private List<Teacher> teachers = new ArrayList<Teacher>();
	
	private Map<String, Object> sessionMap;
	
	@PostConstruct
	public void init () {
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		school = (School) sessionMap.get("school");
		sessionMap.remove ("teacher");
		teachers = teacherDAO.findByInepAndDeleted(school.getSchoolData().getInep());
	}
	
	public String create () {
		return "teacherManage";
	}

	public String edit (Teacher teacher) {
		sessionMap.put ("teacher", teacher);
		return "teacherManage";
	}
	
	public String addStudents (Teacher teacher) {
		sessionMap.put ("teacher", teacher);
		return "import";
	}
	
	public String back () {
		return "home";
	}
	
	
	public String students () {
		return "students";
	}
	
	public String rooms () {
		return "rooms";
	}
	
	
	//Getters and Setters

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
