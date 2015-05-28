package br.com.farofa.gm.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.farofa.gm.model.School;

@ManagedBean
public class SchoolsBean {
	private List<School> schools = new ArrayList<School>();
	
	@PostConstruct
	public void init () {
		
	}
	
	public String create () {
		return "schoolManage";
	}
	
	public String edit () {
		return "schoolManage";
	}
	
	public String back () {
		return "/home";
	}

	public List<School> getSchools() {
		return schools;
	}

	public void setSchools(List<School> schools) {
		this.schools = schools;
	}

}
