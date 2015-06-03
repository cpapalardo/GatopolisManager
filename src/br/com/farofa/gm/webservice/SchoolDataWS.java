package br.com.farofa.gm.webservice;

import br.com.farofa.gm.model.SchoolData;

public class SchoolDataWS extends GenericWSImpl<SchoolData, String> {
	public SchoolDataWS() {
		super(SchoolData.class);
	}
}
