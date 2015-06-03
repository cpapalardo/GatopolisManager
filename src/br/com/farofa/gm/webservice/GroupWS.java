package br.com.farofa.gm.webservice;

import br.com.farofa.gm.model.Group;

public class GroupWS extends GenericWSImpl<Group, Integer> {
	public GroupWS() {
		super(Group.class);
	}
}
