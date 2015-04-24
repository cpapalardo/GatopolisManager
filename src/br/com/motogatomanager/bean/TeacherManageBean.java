package br.com.motogatomanager.bean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.motogatomanager.dao.StudentDAO;
import br.com.motogatomanager.dao.TeacherDAO;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.Student;
import br.com.motogatomanager.modelo.Teacher;

@ManagedBean
public class TeacherManageBean {
	private School school;
	private Teacher teacher;
	private List<Student> students;
	
	private String completeName;
	private String confirmPassword;
	
	private Map<String,Object> sessionMap;

	@PostConstruct
	public void init() {
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		school = (School) sessionMap.get("school");
		teacher = (Teacher) sessionMap.get("teacher");
		students = new StudentDAO ().fetchByTeacher (teacher);
		
		if (teacher != null && teacher.getName() != null) {
			completeName = teacher.getName();
			if (teacher.getLast_name() != null)
				completeName += " " + teacher.getLast_name();
		}
		
	}

	public String save() {
		String name = "";
		String last_name = "";

		if (!completeName.contains(" ")) {
			name = completeName;
		} else {
			name = completeName.substring(0, completeName.indexOf(" "));
			last_name = completeName.substring(completeName.indexOf(" ") + 1,
					completeName.length());
		}

		teacher.setName(name);
		teacher.setLast_name(last_name);
		teacher.setSchool(school);

		if (teacher.getId() == 0) {
			new TeacherDAO ().save(teacher);
		} else {
			new TeacherDAO ().update (teacher);
		}
		
		sessionMap.remove("teacher");
		return "teachers";
	}

	public String back() {
		sessionMap.remove("teacher");
		return "teachers";
	}
	
	public String novoAluno () {
		return "studentManage";
	}
	

	//Getters and Setters
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getCompleteName() {
		return completeName;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
