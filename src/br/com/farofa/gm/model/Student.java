package br.com.farofa.gm.model;

import java.io.Serializable;
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

@SuppressWarnings("serial")
@Entity
@Table(name="student")
@NamedQuery(name="Student.findByInepCode", query="select s from Student s WHERE s.room.teacher.school.schoolData.inep = :inep and s.isDeleted = false")
public class Student extends JsonBehaviour implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="is_deleted", nullable=false)
	private Boolean isDeleted;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at", nullable=false)
	private Date createdAt;
	
	@Column(nullable=false, length=100)
	private String name;
	
	@Column(nullable=true)
	private Character gender;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=true, name="birth_date")
	private Date birthDate;
	
	@Column(nullable=false, length=50)
	private String phase;
	
	@Column(nullable=true)
	private Integer buildings;
	
	@Column(nullable=true)
	private Integer coins;
	
	@Column(nullable=true, name="time_in_city")
	private Integer timeInCity;
	
	@Column(nullable=true, name="app_rating")
	private Integer appRating;
	
	@Column(nullable=true, length=255, name="picture_url")
	private String pictureUrl;
	
	@ManyToOne()
	@JoinColumn(name="room_id", nullable=false)
	private Room room;
	
	public Student() {}
	
	public Student(String json) {
		super.setJson(json);
	}

	public Student(boolean isdeleted, String name, Character gender, Date birth_date, String phase,
			Integer buildings, Integer coins, Integer time_in_city,
			Integer app_rating, String picture_url, Room room) {
		super();
		this.name = name;
		this.gender = gender;
		this.birthDate = birth_date;
		this.phase = phase;
		this.buildings = buildings;
		this.coins = coins;
		this.timeInCity = time_in_city;
		this.appRating = app_rating;
		this.pictureUrl = picture_url;
		this.room = room;
		this.isDeleted = isdeleted;
		this.createdAt = new Date();
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
		return birthDate;
	}

	public void setBirth_date(Date birth_date) {
		this.birthDate = birth_date;
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
		return appRating;
	}

	public void setApp_rating(Integer app_rating) {
		this.appRating = app_rating;
	}

	public String getPicture_url() {
		return pictureUrl;
	}

	public void setPicture_url(String picture_url) {
		this.pictureUrl = picture_url;
	}
	
	
	public Integer getTime_in_city() {
		return timeInCity;
	}

	public void setTime_in_city(Integer time_in_city) {
		this.timeInCity = time_in_city;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", gender=" + gender + ", birth_date="
				+ birthDate + ", phase=" + phase + ", buildings=" + buildings
				+ ", coins=" + coins + ", time_in_city=" + timeInCity
				+ ", app_rating=" + appRating + ", picture_url=" + pictureUrl
				+ ", room=" + room + "]";
	}
}
