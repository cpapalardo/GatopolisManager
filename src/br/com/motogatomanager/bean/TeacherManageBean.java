package br.com.motogatomanager.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.motogatomanager.dao.StudentDAO;
import br.com.motogatomanager.dao.StudentGroupDAO;
import br.com.motogatomanager.dao.StudentGroup_TeacherDAO;
import br.com.motogatomanager.dao.TeacherDAO;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.Student;
import br.com.motogatomanager.modelo.StudentGroup;
import br.com.motogatomanager.modelo.StudentGroup_Teacher;
import br.com.motogatomanager.modelo.Teacher;

@ManagedBean
public class TeacherManageBean {
	private School school;
	private Teacher teacher;
	private List<Student> students;
	private int selectedGroup;
	private List<SelectItem> groupItens;
	
	private boolean isEdited;
	
	private String completeName;
	private String confirmPassword;
	
	private Map<String,Object> sessionMap;
	
	@PostConstruct
	public void init() {
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		school = (School) sessionMap.get("school");
		
		groupItens = new ArrayList<SelectItem> ();
		List<StudentGroup> schoolGroups = new StudentGroupDAO ().fetchBySchool(school);
		
		for (StudentGroup studentGroup : schoolGroups) {
			groupItens.add (new SelectItem (studentGroup.getId(), studentGroup.getName() + " " + studentGroup.getSeries() + " " + studentGroup.getPeriod()));
		}
		
		if (sessionMap.containsKey("teacher")) {
			teacher = (Teacher) sessionMap.get("teacher");
			students = new StudentDAO ().fetchByTeacher (teacher);
			
			completeName = teacher.getName();
			if (teacher.getLast_name() != null)
				completeName += " " + teacher.getLast_name();
			
			isEdited = true;
		} else {
			teacher = new Teacher ();
			isEdited = false;
		}
		
		//Inicia com a primeira turma no selectOneMenu
		List<StudentGroup_Teacher> sg_tList = new StudentGroup_TeacherDAO().fetchByTeacher(teacher);
		if (sg_tList != null & sg_tList.size() > 0) {
			selectedGroup = sg_tList.get(0).getStudentGroup().getId();
		}
		
	}

	public String save() {
		//Valida email
		String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(emailPattern);
		if (!pattern.matcher(teacher.getEmail()).matches()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email inválido!", "Formato de email incorreto.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "teacherManage";
		}
		
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
		
		//Recupera turma selecionada
		StudentGroup group = new StudentGroupDAO ().fetchById(selectedGroup);
		
		//Remove todos os vínculos
		List<StudentGroup_Teacher> sg_tList = new StudentGroup_TeacherDAO().fetchByTeacher(teacher);
		for (StudentGroup_Teacher sg_t : sg_tList) {
			new StudentGroup_TeacherDAO ().Delete(sg_t);
		}
		
		//Adiciona novo vínculo
		StudentGroup_Teacher sg_t = new StudentGroup_Teacher (school, group, teacher);
		new StudentGroup_TeacherDAO().save(sg_t);
		
		sessionMap.remove("teacher");
		
		FacesMessage message = new FacesMessage("Professor adicionado com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		
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

	public boolean getIsEdited() {
		return isEdited;
	}

	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
	}

	public int getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(int selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	public List<SelectItem> getGroupItens() {
		return groupItens;
	}

	public void setGroupItens(List<SelectItem> groupItens) {
		this.groupItens = groupItens;
	}
	
}
