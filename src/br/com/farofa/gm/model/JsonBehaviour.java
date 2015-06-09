package br.com.farofa.gm.model;

import java.io.Serializable;


public interface JsonBehaviour extends Serializable {
	public String getJson();
	public void setJson (String json);
}
