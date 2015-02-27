package br.com.motogatomanager.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.motogatomanager.dao.StudentDAO;
import br.com.motogatomanager.dao.StudentGroupDAO;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.Student;

@ManagedBean
public class StudentBean {
	private School school;
	private List<Student> students = new ArrayList<Student>();
	
	@PostConstruct
	public void init () {
		school = (School) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("school");
		students = new StudentDAO ().fetchBySchool(school);
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
	
	public String groupName (int id) {
		return new StudentGroupDAO ().fetchById(id).getName();
	}
	
}
