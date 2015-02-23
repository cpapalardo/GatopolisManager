package br.com.motogatomanager.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.motogatomanager.db.StaticDB;
import br.com.motogatomanager.modelo.Teacher;

@ManagedBean
public class TeacherManageBean {
	private Teacher teacher;
	private String completeName;
	private String confirmPassword;

	@PostConstruct
	public void init() {
		teacher = (Teacher) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("teacher");
		if (teacher != null) {
			if (teacher.getName() != null) {
				completeName = teacher.getName();
				if (teacher.getLast_name() != null) {
					completeName += " " + teacher.getLast_name();
				}
			}
		}
	}

	public String save() {
		// TODO alterar para id e verificar se ao atualizar está salvando ou
		// atualizando
		teacher.setObjectId("new ID");

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

		// TODO Banco - alterar modo de salvar
		if (!StaticDB.TEACHERS.contains(teacher))
			StaticDB.TEACHERS.add(teacher);
		else
			StaticDB.TEACHERS.set(StaticDB.TEACHERS.indexOf(teacher), teacher);
		return "teachers";
	}

	public String back() {
		return "teachers";
	}

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
}
