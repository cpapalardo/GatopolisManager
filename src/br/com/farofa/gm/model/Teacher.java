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

import org.json.JSONObject;

@Entity
@Table(name = "teacher")
@NamedQuery(name="Teacher.findByInepCode", query="select t from Teacher t WHERE t.school.schoolData.inep = :inep")
public class Teacher implements Serializable, JsonBehaviour {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false, length=255)
	private String name;
	
	@Column(nullable=true, length=45)
	private String nickname;
	
	@Column(nullable=true, length=45)
	private String password;
	
	@Column(nullable=false, length=255)
	private String email;
	
	@Column(nullable=true)
	private Character question;
	
	@Column(nullable=true, length=45)
	private String answer;
	
	@Column(nullable=true, length=255)
	private String picture_url;
	
	@ManyToOne
	@JoinColumn(name="inep", nullable=false, referencedColumnName="inep")
	private School school;

	public Teacher() {}
	
	
	public Teacher(Integer id, String name, String nickname, String password, String email,
			Character question, String answer, String picture_url, School school) {
		super();
		this.id = id;
		this.name = name;
		this.nickname = nickname;
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

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", password="
				+ password + ", email=" + email + ", question=" + question
				+ ", answer=" + answer + ", picture_url=" + picture_url
				+ ", school=" + (school != null ? school.getSchoolData() != null ? school.getSchoolData().getInep() : 0 : 0) + "]";
	}
	
	@Override
	public String getJson() {
		JSONObject jsonObj = new JSONObject();
		if (id != null) jsonObj.put("id", id);
		if (name != null) jsonObj.put("name", name);
		if (nickname != null) jsonObj.put("nickname", nickname);
		if (password != null) jsonObj.put("password", password);
		if (email != null) jsonObj.put("email", email);
		if (question != null) jsonObj.put("question", question);
		if (answer != null) jsonObj.put("answer", answer);
		if (picture_url != null) jsonObj.put("picture_url", picture_url);
		if (school != null && school.getId() != null) jsonObj.put("school", school.getId());
		return jsonObj.toString();
	}

	@Override
	public void setJson(String json) {
		JSONObject jsonObj = new JSONObject(json);
		if (jsonObj.has("id"))
			if (jsonObj.getInt("id") != 0)
				id = jsonObj.getInt("id");
		if (jsonObj.has("name")) name = jsonObj.getString("name");
		if (jsonObj.has("nickname")) nickname = jsonObj.getString("nickname");
		if (jsonObj.has("password")) password = jsonObj.getString("password");
		if (jsonObj.has("email")) email = jsonObj.getString("email");
		if (jsonObj.has("question")) question = jsonObj.getString("question").charAt(0);
		if (jsonObj.has("answer")) answer = jsonObj.getString("answer");
		if (jsonObj.has("picture_url")) picture_url = jsonObj.getString("picture_url");
		if (jsonObj.has("school")) {
			String inep = jsonObj.getString("school");
			
			SchoolData sd = new SchoolData();
			sd.setInep(inep);
			
			school = new School();
			school.setId(inep);
			school.setSchoolData(sd);
		}
	}
	
}
