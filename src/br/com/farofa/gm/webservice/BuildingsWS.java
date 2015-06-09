package br.com.farofa.gm.webservice;

import br.com.farofa.gm.model.Buildings;

public class BuildingsWS extends GenericWSImpl<Buildings, Integer> {
	public BuildingsWS () {
		super(Buildings.class);
	}
}
