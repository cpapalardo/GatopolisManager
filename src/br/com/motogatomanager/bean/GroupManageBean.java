package br.com.motogatomanager.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.motogatomanager.modelo.Group;

@ManagedBean
public class GroupManageBean {
	private Group group;
	
	@PostConstruct
	public void init () {
		
	}
	
	public String save () {
		group.setObjectId("objTeste");
		return "groups";
	}
	
	public String back () {
		return "groups";
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
}
