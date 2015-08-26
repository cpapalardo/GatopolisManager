package br.com.farofa.gm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "teacher")
@NamedQuery(name="Teacher.findByInepCode", query="select t from Teacher t WHERE t.school.schoolData.inep = :inep and t.isDeleted = false")
public class Teacher extends JsonBehaviour implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="is_deleted", nullable=false)
	private Boolean isDeleted;
	
	@Column(nullable=false, length=255)
	private String name;
	
	@Column(nullable=true, length=45)
	private String nickname;
	
	@Column(nullable=true, length=45)
	private String password;
	
	@Column(nullable=false, length=255)
	private String email;
	
	@Column(nullable=true, length=255)
	private String picture_url;
	
	@ManyToOne
	@JoinColumn(name="inep", nullable=false, referencedColumnName="inep")
	private School school;

	public Teacher() {}
	
	public Teacher(String json) {
		super.setJson(json);
	}
	
	public Teacher(String name, String nickname, String password, String email,
			String picture_url, School school) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.picture_url = picture_url;
		this.school = school;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return super.toString() + "Teacher [name=" + name + ", password="
				+ password + ", email=" + email + ", picture_url=" + picture_url
				+ ", school=" + (school != null ? school.getId() : "null") + "]";
	}
	
}
