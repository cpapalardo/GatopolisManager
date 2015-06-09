package br.com.farofa.gm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name="school_data")
@NamedQuery(name="SchoolData.findByInepCode", query="select sd from SchoolData sd WHERE sd.inep = :inep")
public class SchoolData implements Serializable, JsonBehaviour {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable=false, length=8)
	private String inep;
	
	@Column(nullable=false, length=255)
	private String name;
	
	public SchoolData () {}
	
	public SchoolData(String id, String name) {
		super();
		this.inep = id;
		this.name = name;
	}
	
	@Override
	public String getJson() {
		JSONObject jsonObj = new JSONObject();
		if (inep != null) jsonObj.put("inep", inep);
		if (name != null) jsonObj.put("name", name);
		return jsonObj.toString();
	}
	
	@Override
	public void setJson(String json) {
		JSONObject jsonObj = new JSONObject(json);
		if (jsonObj.has("inep")) inep = jsonObj.getString("inep");
		if (jsonObj.has("name")) name = jsonObj.getString("name");
	}
	
	public String getInep() {
		return inep;
	}

	public void setInep(String id) {
		this.inep = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SchoolData [id=" + inep + ", name=" + name + "]";
	}
	
}
