package br.com.farofa.gm.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.farofa.gm.dao.RoomDAO;
import br.com.farofa.gm.dao.StudentDAO;
import br.com.farofa.gm.model.Room;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.Student;

@Named
@RequestScoped
public class StudentManageBean {
	@Inject
	private RoomDAO groupDAO;
	@Inject
	private StudentDAO studentDAO;
	
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
			student.setPhase("NOT_ENOUGH_INPUT");
			student.setRoom(new Room());
			student.setIsDeleted(false);
		}else{
			student = (Student) sessionMap.get("student");
		}
		
		//Load groups
		List<Room> groups = groupDAO.findByInep (school.getSchoolData().getInep());
		studentGroupItens = new ArrayList <SelectItem> ();
		for (Room group : groups) {
			String periodo = "Manhã";
			if (group.getTerm() == 'T')
				periodo = "Tarde";
			else if (group.getTerm() == 'I')
				periodo = "Integral";
			studentGroupItens.add(new SelectItem(group.getId(), group.getName() + " " + group.getSerie() + " " + periodo));			
		}
	}
	
	public String save () {
		if (student.getId() == null) {
			//student.setDiagnosis_level("NOT_ENOUGH_INPUT");
			studentDAO.save(student);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aluno adicionado com sucesso!"));
		} else {
			studentDAO.update (student);
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
