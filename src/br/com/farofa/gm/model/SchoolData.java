package br.com.farofa.gm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="school_data")
@NamedQuery(name="SchoolData.findByInepCode", query="select sd from SchoolData sd WHERE sd.inep = :inep")
public class SchoolData extends JsonBehaviour implements Serializable {
	@Id
	@Column(nullable=false, length=8)
	private String inep;
	
	@Column(nullable=false, length=100)
	private String name;
	
	public SchoolData() {}
	
	public SchoolData(String json) {
		super.setJson(json);
	}
	
	public SchoolData(String id, String name) {
		super();
		this.inep = id;
		this.name = name;
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
