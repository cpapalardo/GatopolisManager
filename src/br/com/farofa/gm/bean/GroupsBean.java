package br.com.farofa.gm.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.farofa.gm.dao.RoomDAO;
import br.com.farofa.gm.dao.RoomDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.Room;
import br.com.farofa.gm.model.School;

@ManagedBean
public class GroupsBean {
	private RoomDAO groupDAO;
	
	private School school;
	private List<Room> groups = new ArrayList<Room>();
	
	private Map<String, Object> sessionMap;
	
	@PostConstruct
	public void init () {
		groupDAO = new RoomDAOImpl(DataBaseManager.getEntityManager());
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		school = (School) sessionMap.get("school");
		groups = groupDAO.findByInep(school.getSchoolData().getInep());
		DataBaseManager.close();
	}
	
	public String novaTurma () {
		return "groupManage";
	}
	
	public String edit (Room group) {
		sessionMap.put("group", group);
		return "groupManage";
	}
	
	public String back () {
		return "teachers";
	}
	
	
	//Getters and Setters
	
	public List<Room> getGroups() {
		return groups;
	}

	public void setGroups(List<Room> groups) {
		this.groups = groups;
	}
}
