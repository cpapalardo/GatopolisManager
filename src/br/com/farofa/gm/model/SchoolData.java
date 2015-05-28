package br.com.farofa.gm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="school_data")
@NamedQuery(name="SchoolData.findByInepCode", query="select sd from SchoolData sd WHERE sd.inep = :inep")
public class SchoolData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer inep;
	
	@Column(nullable=false, length=255)
	private String name;
	
	public SchoolData () {}
	
	public SchoolData(Integer inep, String name) {
		super();
		this.inep = inep;
		this.name = name;
	}

	public Integer getInep() {
		return inep;
	}

	public void setInep(Integer inep) {
		this.inep = inep;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
