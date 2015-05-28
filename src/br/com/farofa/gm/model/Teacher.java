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

@Entity
@Table(name = "teacher")
@NamedQuery(name="Teacher.findByInepCode", query="select t from Teacher t WHERE t.school.schoolData.inep = :inep")
public class Teacher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false, length=255)
	private String name;
	
	@Column(nullable=false)
	private Integer password;
	
	@Column(nullable=true, length=255)
	private String email;
	
	@Column(nullable=true)
	private Character question;
	
	@Column(nullable=true, length=45)
	private String answer;
	
	@Column(nullable=true, length=255)
	private String picture_url;
	
	@ManyToOne
	@JoinColumn(name="school_id", nullable=false, referencedColumnName="inep")
	private School school;

	public Teacher() {}
	
	
	public Teacher(Integer id, String name, Integer password, String email,
			Character question, String answer, String picture_url, School school) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.question = question;
		this.answer = answer;
		this.picture_url = picture_url;
		this.school = school;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPassword() {
		return password;
	}

	public void setPassword(Integer password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Character getQuestion() {
		return question;
	}

	public void setQuestion(Character question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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
	
}