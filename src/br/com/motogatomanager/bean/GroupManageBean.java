package br.com.motogatomanager.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.motogatomanager.dao.StudentGroupDAO;
import br.com.motogatomanager.dao.StudentGroup_TeacherDAO;
import br.com.motogatomanager.dao.TeacherDAO;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.StudentGroup;
import br.com.motogatomanager.modelo.StudentGroup_Teacher;
import br.com.motogatomanager.modelo.Teacher;

@ManagedBean
public class GroupManageBean {
	private School school;
	private StudentGroup group;
	private int teacherId;
	private List<SelectItem> teacherItens;
	
	private Map<String, Object> sessionMap;
	
	@PostConstruct
	public void init () {
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		school = (School) sessionMap.get("school");
		
		group = new StudentGroup ();
		group.setSchool(school);
		group.setPeriod("M");
		
		teacherItens = new ArrayList<SelectItem>();
		List<Teacher> teachers = new TeacherDAO ().fetchBySchool(school);
		for (Teacher teacher : teachers) {
			teacherItens.add(new SelectItem (teacher.getId(), teacher.getName() + " " + teacher.getLast_name()));
		}
	}
	
	public String save () {
		new StudentGroupDAO().save(group);
		
		if (teacherId != 0) {
			Teacher teacher = new Teacher ();
			teacher.setId(teacherId);
			
			StudentGroup_Teacher sg_t = new StudentGroup_Teacher();
			sg_t.setSchool(school);
			sg_t.setStudentGroup(group);
			sg_t.setTeacher_id(teacher);
			
			new StudentGroup_TeacherDAO().save(sg_t);
		}
		return "groups";
	}
	
	public String back () {
		return "groups";
	}

	//Getters and Setters
	
	public StudentGroup getGroup() {
		return group;
	}

	public void setGroup(StudentGroup group) {
		this.group = group;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public List<SelectItem> getTeacherItens() {
		return teacherItens;
	}

	public void setTeacherItens(List<SelectItem> teacherItens) {
		this.teacherItens = teacherItens;
	}
	
}
