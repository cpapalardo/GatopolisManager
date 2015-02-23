package br.com.motogatomanager.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.motogatomanager.db.BancoLocal;
import br.com.motogatomanager.modelo.Group;

@ManagedBean
public class GroupBean {
	
	private List<Group> groups = new ArrayList<Group>();
	
	@PostConstruct
	public void init () {
		groups = BancoLocal.GROUPS;
	}
	
	public String create () {
		return "groupManage";
	}
	
	public String edit () {
		return "groupManage";
	}
	
	public String back () {
		return "teachers";
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
}
