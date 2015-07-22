package br.com.farofa.gm.webservice;

import java.io.Serializable;

import br.com.farofa.gm.model.JsonBehaviour;

public interface GenericWS<T extends JsonBehaviour, PK extends Serializable> {
	public String save(String json);
	
	public String update(String json);
	
	public String delete(String json);
	
	public String findById(PK pk);
	
	public String findByInep (String inep);
	
	public String findAll();
}
