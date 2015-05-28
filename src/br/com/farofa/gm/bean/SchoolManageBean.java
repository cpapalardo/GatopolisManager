package br.com.farofa.gm.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.farofa.gm.model.School;

@ManagedBean
public class SchoolManageBean {
	private School school;
	
	@PostConstruct
	public void init () {
		
	}
	
	public String save () {
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
