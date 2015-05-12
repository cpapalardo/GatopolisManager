package br.com.motogatomanager.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.motogatomanager.dao.StudentDAO;
import br.com.motogatomanager.dao.StudentGroupDAO;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.Student;
import br.com.motogatomanager.modelo.StudentGroup;
import br.com.motogatomanager.modelo.Teacher;

@ManagedBean
public class StudentManageBean {
	private School school;
	private Teacher teacher;
	private String completeName;
	private Student student;
	private int studentGroupId;
	private List<SelectItem> studentGroupItens;
	
	private Map <String, Object> sessionMap;
	
	@PostConstruct
	public void init () {
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		school = (School) sessionMap.get("school");
		
		studentGroupItens = new ArrayList <SelectItem> ();
		student = new Student();
		student.setGender("Menino");
		
		List<StudentGroup> studentGroups = null;
		if (sessionMap.containsKey("teacher")) {
			teacher = (Teacher) sessionMap.get("teacher");
			studentGroups = new StudentGroupDAO ().fetchByTeacher (teacher);
		} else {
			studentGroups = new StudentGroupDAO ().fetchBySchool (school);
		}
		
		for (StudentGroup studentGroup : studentGroups) {
			studentGroupItens.add(new SelectItem(studentGroup.getId(), studentGroup.getName() + " " + studentGroup.getSeries() + " " + studentGroup.getPeriod()));			
		}
	}
	
	public String save () {
		String name = "";
		String last_name = "";

		if (!completeName.contains(" ")) {
			name = completeName;
		} else {
			name = completeName.substring(0, completeName.indexOf(" "));
			last_name = completeName.substring(completeName.indexOf(" ") + 1, completeName.length());
		}

		student.setSchool(school);
		student.setName(name);
		student.setLast_name(last_name);
		
		StudentGroup studentGroup = new StudentGroup ();
		studentGroup.setId(studentGroupId);
		student.setStudent_group(studentGroup);

		if (student.getId() == 0) {
			student.setDiagnosis_level("NOT_ENOUGH_INPUT");
			new StudentDAO().save(student);
		} else {
			new StudentDAO().update (student);
		}
		
		FacesMessage message = new FacesMessage("Aluno adicionado com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		if (sessionMap.containsKey("teacher")) {
			return "teacherManage";
		}else{
			return "students";
		}
	}
	
	public String back () {
		if (sessionMap.containsKey("teacher"))
			return "teacherManage";
		else
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

	public int getStudentGroupId() {
		return studentGroupId;
	}

	public void setStudentGroupId(int studentGroupId) {
		this.studentGroupId = studentGroupId;
	}

	public String getCompleteName() {
		return completeName;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

}
