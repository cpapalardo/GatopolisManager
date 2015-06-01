package br.com.farofa.gm.bean;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.farofa.gm.dao.StudentDAO;
import br.com.farofa.gm.dao.StudentDAOImpl;
import br.com.farofa.gm.dao.TeacherDAO;
import br.com.farofa.gm.dao.TeacherDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.Student;
import br.com.farofa.gm.model.Teacher;

@ManagedBean
public class TeacherManageBean {
	private TeacherDAO teacherDAO;
	private StudentDAO studentDAO;
	
	private School school;
	private Teacher teacher;
	private List<Student> students;
	
	private boolean isEdited;
	
	private String confirmPassword;
	
	private Map<String,Object> sessionMap;
	
	@PostConstruct
	public void init() {
		studentDAO = new StudentDAOImpl(DataBaseManager.getEntityManager());
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		school = (School) sessionMap.get("school");
		
		if (sessionMap.containsKey("teacher")) {
			teacher = (Teacher) sessionMap.get("teacher");
			students = studentDAO.findByTeacher (teacher);
			isEdited = true;
		} else {
			teacher = new Teacher ();
			isEdited = false;
		}
	}

	public String save() {
		teacherDAO = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		
		//Valida email
		String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(emailPattern);
		if (!pattern.matcher(teacher.getEmail()).matches()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email inválido!", "Formato de email incorreto.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "teacherManage";
		}
		//end
		
		teacher.setSchool(school);

		if (teacher.getId() == null) {
			teacherDAO.save(teacher);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Professor adicionado com sucesso!"));
		} else {
			teacherDAO.update (teacher);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Professor alterado com sucesso!"));
		}
		
		sessionMap.remove("teacher");
		DataBaseManager.close();
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public boolean getIsEdited() {
		return isEdited;
	}

	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
	}
	
}
