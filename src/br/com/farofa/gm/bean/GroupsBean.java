package br.com.farofa.gm.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.farofa.gm.dao.GroupDAOImpl;
import br.com.farofa.gm.model.Group;
import br.com.farofa.gm.model.School;

@ManagedBean
public class GroupsBean {
	private School school;
	private List<Group> groups = new ArrayList<Group>();
	
	private Map<String, Object> sessionMap;
	
	@PostConstruct
	public void init () {
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		school = (School) sessionMap.get("school");
		groups = new GroupDAOImpl().findByInep(school.getSchoolData().getInep());
	}
	
	public String novaTurma () {
		return "groupManage";
	}
	
	public String edit (Group group) {
		sessionMap.put("group", group);
		return "groupManage";
	}
	
	public String back () {
		return "teachers";
	}
	
	
	//Getters and Setters
	
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
}
