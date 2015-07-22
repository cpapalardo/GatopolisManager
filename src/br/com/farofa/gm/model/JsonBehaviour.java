package br.com.farofa.gm.model;

import java.io.Serializable;


public interface JsonBehaviour extends Serializable {
	public String getJson();
	void setJson (String json);
}
