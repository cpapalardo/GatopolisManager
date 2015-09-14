package br.com.farofa.gm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name="school")
@NamedQuery(name="School.findByInepCode", query="select s from School s WHERE s.schoolData.inep = :inep")
public class School extends JsonBehaviour implements Serializable {
	@Id
	@Column(name="inep")
	private String id;
	
	@Column(nullable=false, length=9)
	private String sync_code;
	
	@Column(nullable=false, length=45)
	private String password;
	
	@Column(nullable=true, length= 255)
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true, name="created_at")
	private Date createdAt;
	
	@OneToOne
	@PrimaryKeyJoinColumn(name="inep")
	private SchoolData schoolData;
	
	public School() {}
	
	public School(String json) {
		super.setJson(json);
	}
	
	public School(String id, String sync_code, String password,
			String email, SchoolData schoolData) {
		super();
		this.id = id;
		this.sync_code = sync_code;
		this.password = password;
		this.email = email;
		this.schoolData = schoolData;
	}
	
	public SchoolData getSchoolData() {
		return schoolData;
	}
	public void setSchoolData(SchoolData schoolData) {
		this.schoolData = schoolData;
	}
	public String getSync_code() {
		return sync_code;
	}
	public void setSync_code(String sync_code) {
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "School [" + "id=" + id + ", sync_code=" + sync_code
				+ ", password=" + password + ", email=" + email + ", schoolData=" + (schoolData != null ? schoolData.getInep() : "null") + "]";
	}
}
