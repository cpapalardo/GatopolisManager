package br.com.motogatomanager.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.motogatomanager.DB.StaticDB;
import br.com.motogatomanager.modelo.School;

@ManagedBean
public class SchoolManageBean {
	private School school;
	
	@PostConstruct
	public void init () {
		
	}
	
	public String save () {
		school.setObjectId("objTeste");
		StaticDB.SCHOOLS.add(school);
		return "schools";
	}
	
	public String back () {
		return "schools";
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

}
