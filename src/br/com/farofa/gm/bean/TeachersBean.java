package br.com.farofa.gm.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.farofa.gm.dao.TeacherDAO;
import br.com.farofa.gm.dao.TeacherDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.Teacher;

@ManagedBean
public class TeachersBean {
	private TeacherDAO teacherDAO;
	
	private School school;
	private Teacher teacher;
	private List<Teacher> teachers = new ArrayList<Teacher>();
	
	private Map<String, Object> sessionMap;
	
	@PostConstruct
	public void init () {
		teacherDAO = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		school = (School) sessionMap.get("school");
		sessionMap.remove ("teacher");
		teachers = teacherDAO.findByInep(school.getSchoolData().getInep());
		DataBaseManager.close();
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
	
	public String groups () {
		return "groups";
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
