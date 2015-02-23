package br.com.motogatomanager.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.motogatomanager.db.StaticDB;
import br.com.motogatomanager.modelo.School;

@ManagedBean
public class SchoolBean {
	private List<School> schools = new ArrayList<School>();
	
	@PostConstruct
	public void init () {
		schools = StaticDB.SCHOOLS;
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
