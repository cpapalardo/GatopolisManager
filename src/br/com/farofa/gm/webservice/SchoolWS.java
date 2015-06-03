package br.com.farofa.gm.webservice;

import br.com.farofa.gm.model.School;

public class SchoolWS extends GenericWSImpl<School, String> {
	public SchoolWS() {
		super(School.class);
	}
}
