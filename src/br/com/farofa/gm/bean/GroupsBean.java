package br.com.farofa.gm.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.farofa.gm.dao.GroupDAO;
import br.com.farofa.gm.dao.GroupDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.Group;
import br.com.farofa.gm.model.School;

@ManagedBean
public class GroupsBean {
	private GroupDAO groupDAO;
	
	private School school;
	private List<Group> groups = new ArrayList<Group>();
	
	private Map<String, Object> sessionMap;
	
	@PostConstruct
	public void init () {
		groupDAO = new GroupDAOImpl(DataBaseManager.getEntityManager());
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		school = (School) sessionMap.get("school");
		groups = groupDAO.findByInep(school.getSchoolData().getInep());
		DataBaseManager.close();
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
