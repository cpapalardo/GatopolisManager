package br.com.farofa.gm.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.farofa.gm.dao.RoomDAO;
import br.com.farofa.gm.dao.RoomDAOImpl;
import br.com.farofa.gm.dao.TeacherDAO;
import br.com.farofa.gm.dao.TeacherDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.Room;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.Teacher;

@ManagedBean
public class RoomManageBean {
	private TeacherDAO teacherDAO;
	private RoomDAO roomDAO;
	
	private School school;
	private Room room;
	private List<SelectItem> teacherItens;
	
	private Map<String, Object> sessionMap;
	
	@PostConstruct
	public void init () {
		teacherDAO = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		school = (School) sessionMap.get("school");
		
		//Load teachers
		teacherItens = new ArrayList<SelectItem>();
		List<Teacher> teachers = teacherDAO.findByInep(school.getSchoolData().getInep());
		for (Teacher teacher : teachers) {
			teacherItens.add(new SelectItem (teacher.getId(), teacher.getName()));
		}
		
		//Initiate room
		if (sessionMap.containsKey("room")) {
			room = (Room) sessionMap.get("room");
		} else {
			room = new Room ();
			room.setTerm('M');
			room.setTeacher(new Teacher());
		}
		DataBaseManager.close();
	}
	
	public String save () {
		roomDAO = new RoomDAOImpl(DataBaseManager.getEntityManager());
		if(room.getId() == null) {
			roomDAO.save(room);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Turma adicionada com sucesso!"));
		} else {
			roomDAO.update(room);
			sessionMap.remove("room");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Turma alterada com sucesso!"));
		}
		DataBaseManager.close();
		return "rooms";		
	}
	
	public String back () {
		sessionMap.remove("room");
		return "rooms";
	}

	//Getters and Setters
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<SelectItem> getTeacherItens() {
		return teacherItens;
	}

	public void setTeacherItens(List<SelectItem> teacherItens) {
		this.teacherItens = teacherItens;
	}
	
}
