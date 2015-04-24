package br.com.motogatomanager.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.motogatomanager.dao.StudentGroupDAO;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.StudentGroup;

@ManagedBean
public class GroupsBean {
	private School school;
	private List<StudentGroup> groups = new ArrayList<StudentGroup>();
	
	private Map<String, Object> sessionMap;
	
	@PostConstruct
	public void init () {
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		school = (School) sessionMap.get("school");
		groups = new StudentGroupDAO().fetchBySchool(school);
	}
	
	public String novaTurma () {
		return "groupManage";
	}
	
	public String edit () {
		return "groupManage";
	}
	
	public String back () {
		return "teachers";
	}
	
	
	//Getters and Setters
	
	public List<StudentGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<StudentGroup> groups) {
		this.groups = groups;
	}
}
