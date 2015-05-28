package br.com.farofa.gm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="school")
@NamedQuery(name="School.findByInepCode", query="select s from School s WHERE s.schoolData.inep = :inep")
public class School implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne
	@JoinColumn(name="inep")
	private SchoolData schoolData;
	
	@Column(nullable=false)
	private Integer sync_code;
	
	@Column(nullable=false, length=8)
	private String password;
	
	@Column(nullable=false, length= 255)
	private String email;
	
	public School () {}
	
	public School(SchoolData schoolData, Integer sync_code, String password,
			String email) {
		super();
		this.schoolData = schoolData;
		this.sync_code = sync_code;
		this.password = password;
		this.email = email;
	}

	public SchoolData getSchoolData() {
		return schoolData;
	}
	public void setSchoolData(SchoolData schoolData) {
		this.schoolData = schoolData;
	}
	public Integer getSync_code() {
		return sync_code;
	}
	public void setSync_code(Integer sync_code) {
		this.sync_code = sync_code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}