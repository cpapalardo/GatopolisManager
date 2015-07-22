package br.com.farofa.gm.dao;

import javax.inject.Named;

import br.com.farofa.gm.model.School;

@Named
public interface SchoolDAO extends GenericDAO<School, String> {
	
	public School findBySyncCode(String syncCode);
	public School findByName(String name);
}
