package br.com.farofa.gm.dao;

import br.com.farofa.gm.model.School;

public interface SchoolDAO extends GenericDAO<School, String> {
	
	public School findBySyncCode(String syncCode);
	public School findByName(String name);
}
