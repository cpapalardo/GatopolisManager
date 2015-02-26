package br.com.motogatomanager.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.motogatomanager.dao.SchoolDAO;
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
public class HomeBean {
	private String syncCode;
	
	@PostConstruct
	public void init () {
		
		/*for (School s : new SchoolDAO ().fetchAll())
			System.out.println(s);
		for (Teacher t : new TeacherDAO ().fetchAll())
			System.out.println(t);
		for (StudentGroup sg : new StudentGroupDAO ().fetchAll())
			System.out.println(sg);
		for (Student s : new StudentDAO ().fetchAll())
			System.out.println(s);
		for (StudentGroup_Teacher sg_t : new StudentGroup_TeacherDAO ().fetchAll())
			System.out.println(sg_t);*/
		
		/*School school = new SchoolDAO ().fetchBySyncCode("1");
		System.out.println(school);
		for (Teacher t : new TeacherDAO ().fetchBySchool(school))
			System.out.println(t);
		for (StudentGroup sg : new StudentGroupDAO ().fetchBySchool(school))
			System.out.println(sg);
		for (Student s : new StudentDAO ().fetchBySchool(school))
			System.out.println(s);
		/*for (StudentGroup_Teacher sg_t : new StudentGroup_TeacherDAO ().fetchBySchool())
			System.out.println(sg_t);*/
		
		/*School school = new School ("1234", "Escola teste", "1234", "1234");
		//new SchoolDAO ().save(school);
		school.setId(1);
				
		Teacher t = new Teacher ("Professor X", "Xavier", "1234", "qwer@qwer.com", true, "a", "a", school);
		//new TeacherDAO ().save(t);
		t.setId(2);
		
		
		Student s = new Student ("Aluno Wow", "Warrior", "Troll", new Date (), "Natureza", "noob", 1, 1, school, sg);
		//new StudentDAO ().save(s);
		s.setId(1);
		
		StudentGroup_Teacher sg_t = new StudentGroup_Teacher();
		//new StudentGroup_TeacherDAO ().save(sg_t);
		sg_t.setId(1);*/
	}
	
	public String access () {
		if (syncCode != null && !syncCode.equals("")) {
			//School school = BancoLocal.SCHOOLS.get(0);
			School school = new SchoolDAO ().fetchBySyncCode (syncCode);
			
			if (school.getSync_code() != null && school.getSync_code().equals(syncCode)) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("schoolName", school.getName());  
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("schoolSyncCode", school.getSync_code());  
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("school", school);  
				return "Manager/teachers";
			} else {
		        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Código de Sincronização não encontrado."));
		        return "home";
			}
		} else {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "O campo Código de Sincronização deve ser preenchido."));
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
