package br.com.farofa.gm.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.farofa.gatopolisws.dao.RoomDAO;
import br.com.farofa.gatopolisws.model.Room;
import br.com.farofa.gatopolisws.model.School;

@Named
@RequestScoped
public class RoomsBean {
	@Inject
	private RoomDAO roomDAO;
	
	private School school;
	private List<Room> rooms = new ArrayList<Room>();
	
	private Map<String, Object> sessionMap;
	
	@PostConstruct
	public void init () {
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		school = (School) sessionMap.get("school");
		rooms = roomDAO.findByInep(school.getSchoolData().getInep());
	}
	
	public String novaTurma () {
		return "roomManage";
	}
	
	public String edit (Room room) {
		sessionMap.put("room", room);
		return "roomManage";
	}
	
	public String back () {
		return "teachers";
	}
	
	
	//Getters and Setters
	
	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
}
