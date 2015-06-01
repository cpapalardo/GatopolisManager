package br.com.farofa.gm.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.farofa.gm.dao.GroupDAO;
import br.com.farofa.gm.dao.GroupDAOImpl;
import br.com.farofa.gm.dao.TeacherDAO;
import br.com.farofa.gm.dao.TeacherDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.Group;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.Teacher;

@ManagedBean
public class GroupManageBean {
	private TeacherDAO teacherDAO;
	private GroupDAO groupDAO;
	
	private School school;
	private Group group;
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
		
		//Initiate group
		if (sessionMap.containsKey("group")) {
			group = (Group) sessionMap.get("group");
		} else {
			group = new Group ();
			group.setPeriod('M');
			group.setTeacher(new Teacher());
		}
		DataBaseManager.close();
	}
	
	public String save () {
		groupDAO = new GroupDAOImpl(DataBaseManager.getEntityManager());
		if(group.getId() == null) {
			groupDAO.save(group);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Turma adicionada com sucesso!"));
		} else {
			groupDAO.update(group);
			sessionMap.remove("group");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Turma alterada com sucesso!"));
		}
		DataBaseManager.close();
		return "groups";		
	}
	
	public String back () {
		sessionMap.remove("group");
		return "groups";
	}

	//Getters and Setters
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<SelectItem> getTeacherItens() {
		return teacherItens;
	}

	public void setTeacherItens(List<SelectItem> teacherItens) {
		this.teacherItens = teacherItens;
	}
	
}
