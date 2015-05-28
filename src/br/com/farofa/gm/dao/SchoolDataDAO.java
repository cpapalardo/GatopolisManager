package br.com.farofa.gm.dao;

import java.util.List;

import br.com.farofa.gm.model.SchoolData;

public interface SchoolDataDAO extends GenericDAO<SchoolData, Integer> {
	public List<SchoolData> findByInep();
}
