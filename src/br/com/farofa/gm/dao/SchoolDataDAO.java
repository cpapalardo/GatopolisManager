package br.com.farofa.gm.dao;

import java.util.List;

import br.com.farofa.gm.model.SchoolData;

public interface SchoolDataDAO extends GenericDAO<SchoolData, String> {
	public List<SchoolData> findByInep(String inep);
}
