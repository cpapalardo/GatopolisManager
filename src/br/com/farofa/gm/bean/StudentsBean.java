package br.com.farofa.gm.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.farofa.gatopolisws.dao.RoomDAO;
import br.com.farofa.gatopolisws.dao.StudentDAO;
import br.com.farofa.gatopolisws.model.School;
import br.com.farofa.gatopolisws.model.Student;

@Named
@RequestScoped
public class StudentsBean {
	@Inject
	private RoomDAO groupDAO;
	@Inject
	private StudentDAO studentDAO;
	
	private School school;
	private List<Student> students = new ArrayList<Student>();
	
	private Map<String,Object> sessionMap;
	
	@PostConstruct
	public void init () {
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		school = (School) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("school");
		students = studentDAO.findByInep(school.getSchoolData().getInep());
	}
	
	public String novoAluno () {
		return "studentManage";
	}
	
	public String edit (Student student) {
		sessionMap.put("student", student);
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
		String name = null;
		name = groupDAO.findById(id).getName();
		return name;
	}
	
}
