package br.com.farofa.gm.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.farofa.gm.dao.GroupDAOImpl;
import br.com.farofa.gm.dao.StudentDAOImpl;
import br.com.farofa.gm.model.Group;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.Student;

@ManagedBean
public class StudentManageBean {
	private School school;
	private Student student;
	private List<SelectItem> studentGroupItens;
	
	private Map <String, Object> sessionMap;
	
	@PostConstruct
	public void init () {
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		school = (School) sessionMap.get("school");
		
		//Define se é isert ou update
		if(!sessionMap.containsKey("student")){
			student = new Student();
			student.setGender('M');
			student.setGroup(new Group());
		}else{
			student = (Student) sessionMap.get("student");
		}
		
		//Load groups
		List<Group> groups = new GroupDAOImpl ().findByInep (school.getSchoolData().getInep());
		studentGroupItens = new ArrayList <SelectItem> ();
		for (Group group : groups) {
			studentGroupItens.add(new SelectItem(group.getId(), group.getName() + " " + group.getSerie() + " " + group.getPeriod()));			
		}
	}
	
	public String save () {
		if (student.getId() == null) {
			student.setDiagnosis_level("NOT_ENOUGH_INPUT");
			new StudentDAOImpl().save(student);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aluno adicionado com sucesso!"));
		} else {
			new StudentDAOImpl().update (student);
			sessionMap.remove("student");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aluno alterado com sucesso!"));
		}
		
		return "students";
	}
	
	public String back () {
		sessionMap.remove("student");
		return "students";
	}

	//Getters and Setters
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public List<SelectItem> getStudentGroupItens() {
		return studentGroupItens;
	}

	public void setStudentGroupItens(List<SelectItem> studentGroupItens) {
		this.studentGroupItens = studentGroupItens;
	}

}
