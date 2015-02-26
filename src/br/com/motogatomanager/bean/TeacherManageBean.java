package br.com.motogatomanager.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.motogatomanager.dao.TeacherDAO;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.Teacher;

@ManagedBean
public class TeacherManageBean {
	private School school;
	private Teacher teacher;
	private String completeName;
	private String confirmPassword;

	@PostConstruct
	public void init() {
		school = (School) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("school");
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
