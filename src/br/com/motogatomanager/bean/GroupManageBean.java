package br.com.motogatomanager.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.motogatomanager.modelo.StudentGroup;

@ManagedBean
public class GroupManageBean {
	private StudentGroup group;
	
	@PostConstruct
	public void init () {
		
	}
	
	public String save () {
		//group.setObjectId("objTeste");
		return "groups";
	}
	
	public String back () {
		return "groups";
	}

	public StudentGroup getGroup() {
		return group;
	}

	public void setGroup(StudentGroup group) {
		this.group = group;
	}
	
}
