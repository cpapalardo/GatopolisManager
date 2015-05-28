package br.com.farofa.gm.dao;

import br.com.farofa.gm.model.School;

public interface SchoolDAO extends GenericDAO<School, Integer> {
	
	public School findBySyncCode(int syncCode);
	public School findByName(String name);
}
