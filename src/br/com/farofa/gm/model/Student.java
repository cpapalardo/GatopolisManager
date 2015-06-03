package br.com.farofa.gm.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="student")
@NamedQuery(name="Student.findByInepCode", query="select s from Student s WHERE s.group.teacher.school.schoolData.inep = :inep")
public class Student extends JsonBehaviour implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false, length=255)
	private String name;
	
	@Column(nullable=false)
	private Character gender;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date birth_date;
	
	@Column(nullable=false, length=45)
	private String diagnosis_level;
	
	@Column(nullable=true)
	private Integer buildings;
	
	@Column(nullable=true)
	private Integer coins;
	
	@Column(nullable=true)
	private Integer app_rating;
	
	@Column(nullable=true)
	private String picture_url;
	
	@ManyToOne()
	@JoinColumn(name="class_id", nullable=false)
	private Group group;
	
	public Student () {}

	public Student(Integer id, String name, Character gender, Date birth_date,
			String diagnosis_level, Integer buildings, Integer coins,
			Integer app_rating, String picture_url, Group group) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birth_date = birth_date;
		this.diagnosis_level = diagnosis_level;
		this.buildings = buildings;
		this.coins = coins;
		this.app_rating = app_rating;
		this.picture_url = picture_url;
		this.group = group;
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

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public String getDiagnosis_level() {
		return diagnosis_level;
	}

	public void setDiagnosis_level(String diagnosis_level) {
		this.diagnosis_level = diagnosis_level;
	}

	public Integer getBuildings() {
		return buildings;
	}

	public void setBuildings(Integer buildings) {
		this.buildings = buildings;
	}

	public Integer getCoins() {
		return coins;
	}

	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public Integer getApp_rating() {
		return app_rating;
	}

	public void setApp_rating(Integer app_rating) {
		this.app_rating = app_rating;
	}

	public String getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return "Student [id=" + id + ", name=" + name + ", gender=" + gender
				+ ", birth_date=" + df.format(birth_date) + ", diagnosis_level="
				+ diagnosis_level + ", buildings=" + buildings + ", coins="
				+ coins + ", app_rating=" + app_rating + ", picture_url="
				+ picture_url + ", group=" + group.getId() + "]";
	}
	
}
