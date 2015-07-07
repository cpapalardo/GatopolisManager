package br.com.farofa.gm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name="school")
@NamedQuery(name="School.findByInepCode", query="select s from School s WHERE s.schoolData.inep = :inep")
public class School implements Serializable, JsonBehaviour {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="inep")
	private String id;
	
	@Column(nullable=false, length=9)
	private String sync_code;
	
	@Column(nullable=false, length=45)
	private String password;
	
	@Column(nullable=true, length= 255)
	private String email;
	
	@OneToOne
	@PrimaryKeyJoinColumn(name="inep")
	private SchoolData schoolData;
	
	public School () {}
	
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
	
	@Override
	public String toString() {
		return "School [" + "id=" + id + ", sync_code=" + sync_code
				+ ", password=" + password + ", email=" + email + ", schoolData=" + (schoolData != null ? schoolData.getInep() : "null") + "]";
	}

	@Override
	public String getJson() {
		JSONObject jsonObj = new JSONObject();
		if (id != null) jsonObj.put("id", id);
		if (sync_code != null) jsonObj.put("sync_code", sync_code);
		if (password != null) jsonObj.put("password", password);
		if (email != null) jsonObj.put("email", email);
		if (schoolData != null) {
			JSONObject subJson = new JSONObject();
			subJson.put("inep", schoolData.getInep());
			subJson.put("name", schoolData.getName());
			jsonObj.put("schoolData", subJson);
		}
		return jsonObj.toString();
	}

	@Override
	public void setJson(String json) {
		JSONObject jsonObj = new JSONObject(json);
		if (jsonObj.has("id")) id = jsonObj.getString("id");
		if (jsonObj.has("sync_code")) sync_code = jsonObj.getString("sync_code");
		if (jsonObj.has("password")) password = jsonObj.getString("password");
		if (jsonObj.has("email")) email = jsonObj.getString("email");
		if (jsonObj.has("schoolData")) {
			JSONObject subJson = (JSONObject)jsonObj.get("schoolData");
			schoolData = new SchoolData();
			id = subJson.getString("inep");
			schoolData.setInep(subJson.getString("inep"));
			schoolData.setName(subJson.getString("name"));
		}
	}
}
