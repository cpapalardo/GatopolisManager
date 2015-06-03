package br.com.farofa.gm.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.farofa.gm.dao.RoomDAO;
import br.com.farofa.gm.dao.RoomDAOImpl;
import br.com.farofa.gm.dao.StudentDAO;
import br.com.farofa.gm.dao.StudentDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.Student;

@ManagedBean
public class StudentsBean {
	private RoomDAO groupDAO;
	private StudentDAO studentDAO;
	
	private School school;
	private List<Student> students = new ArrayList<Student>();
	
	private Map<String,Object> sessionMap;
	
	@PostConstruct
	public void init () {
		studentDAO = new StudentDAOImpl(DataBaseManager.getEntityManager());
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		school = (School) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("school");
		students = studentDAO.findByInep(school.getSchoolData().getInep());
		DataBaseManager.close();
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
		groupDAO = new RoomDAOImpl(DataBaseManager.getEntityManager());
		name = groupDAO.findById(id).getName();
		DataBaseManager.close();
		return name;
	}
	
}
