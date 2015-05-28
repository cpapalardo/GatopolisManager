package br.com.farofa.gm.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.farofa.gm.dao.GroupDAOImpl;
import br.com.farofa.gm.dao.StudentDAOImpl;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.Student;

@ManagedBean
public class StudentsBean {
	private School school;
	private List<Student> students = new ArrayList<Student>();
	
	private Map<String,Object> sessionMap;
	
	@PostConstruct
	public void init () {
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		school = (School) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("school");
		students = new StudentDAOImpl ().findByInep(school.getSchoolData().getInep());
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
		return new GroupDAOImpl ().findById(id).getName();
	}
	
}