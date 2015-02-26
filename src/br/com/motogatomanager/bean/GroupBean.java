package br.com.motogatomanager.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.motogatomanager.dao.StudentGroupDAO;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.StudentGroup;

@ManagedBean
public class GroupBean {
	private School school;
	private List<StudentGroup> groups = new ArrayList<StudentGroup>();
	
	@PostConstruct
	public void init () {
		school = (School) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("school");
		groups = new StudentGroupDAO().fetchBySchool(school);
	}
	
	public String create () {
		return "groupManage";
	}
	
	public String edit () {
		return "groupManage";
	}
	
	public String back () {
		return "teachers";
	}

	public List<StudentGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<StudentGroup> groups) {
		this.groups = groups;
	}
}
