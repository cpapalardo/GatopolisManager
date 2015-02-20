package br.com.motogatomanager.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class HomeBean {
	private String syncCode;
	
	@PostConstruct
	public void init () {
		/*School school = null;
		Teacher teacher = null;
		Group group = null;
		Student student = null;
		
		if (StaticDB.SCHOOLS == null) {
			StaticDB.SCHOOLS = new ArrayList<School>();
			school = new School ("1234", "Escola X", "1234", "1234");
			school.setObjectId("x4iv8ky");
			StaticDB.SCHOOLS.add(school);
		}
		
		if (StaticDB.TEACHERS == null) {
			StaticDB.TEACHERS = new ArrayList<Teacher>();
			teacher = new Teacher ("Sobrenome", "Nome", "1234", "email@email.com", true, "question", "answer", school, new byte[3]);
			teacher.setObjectId("192j3u4");
			StaticDB.TEACHERS.add(teacher);
		}
		
		if (StaticDB.GROUPS == null) {
			StaticDB.GROUPS = new ArrayList<Group>();
			group = new Group ("Turma X", "1º ano A", "Manhã", null, school);
			group.setObjectId("1j902j3h");
			StaticDB.GROUPS.add(group);
		}
		
		if (StaticDB.STUDENTS == null) {
			StaticDB.STUDENTS = new ArrayList<Student>();
			student = new Student ("Aluno", "Sobrenome", "M", 1, "Pai", "xxx", 1, 1, school, group);
			student.setObjectId("asdgdsa");
			StaticDB.STUDENTS.add(student);
		}*/
	}
	
	public String access () {
		if (syncCode != null && !syncCode.equals("")) {
			return "Manager/teachers";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
			return "home";
		}
	}

	public String getSyncCode() {
		return syncCode;
	}

	public void setSyncCode(String syncCode) {
		this.syncCode = syncCode;
	}
	
	/*public String listarEscolas() {
		return "Manager/schools";
	}

	public String listarProfessores() {
		return "Manager/teachers";
	}

	public String listarTurmas() {
		return "Manager/groups";
	}

	public String listarAlunos() {
		return "Manager/students";
	}

	public String importar() {
		return "Manager/import";
	}*/
	
	
}
