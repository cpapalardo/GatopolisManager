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
public class RoomsBean {
	private RoomDAO roomDAO;
	
	private School school;
	private List<Room> rooms = new ArrayList<Room>();
	
	private Map<String, Object> sessionMap;
	
	@PostConstruct
	public void init () {
		roomDAO = new RoomDAOImpl(DataBaseManager.getEntityManager());
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		school = (School) sessionMap.get("school");
		rooms = roomDAO.findByInep(school.getSchoolData().getInep());
		DataBaseManager.close();
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
