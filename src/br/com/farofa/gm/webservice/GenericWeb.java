package br.com.farofa.gm.webservice;

import java.io.Serializable;

public interface GenericWeb<T extends Serializable, PkType extends Serializable> {
	public void save(String json);
	
	public void update(String json);
	
	public void delete(String json);
	
	public String findById(PkType pk);
	
	public String findByInep (String inep);
	
	public String findAll();
}
