package br.com.farofa.gm.dao;

import java.util.List;

import javax.inject.Named;

import br.com.farofa.gm.model.SchoolData;

@Named
public interface SchoolDataDAO extends GenericDAO<SchoolData, String> {
	public List<SchoolData> findByInep(String inep);
}
