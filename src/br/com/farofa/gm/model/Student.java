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
	@Column(name="created_at", nullable=true)
	private Date createdAt;
	
	@Column(nullable=false, length=255)
	private String name;
	
	@Column(nullable=false)
	private Character gender;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date birth_date;
	
	@Column(nullable=false, length=45)
	private String phase;
	
	@Column(nullable=true)
	private Integer buildings;
	
	@Column(nullable=true)
	private Integer coins;
	
	@Column(nullable=true)
	private Integer time_in_city;
	
	@Column(nullable=true)
	private Integer app_rating;
	
	@Column(nullable=true)
	private String picture_url;
	
	@ManyToOne()
	@JoinColumn(name="room_id", nullable=false)
	private Room room;
	
	public Student() {}
	
	public Student(String json) {
		super.setJson(json);
	}

	public Student(String name, Character gender, Date birth_date, String phase,
			Integer buildings, Integer coins, Integer time_in_city,
			Integer app_rating, String picture_url, Room room) {
		super();
		this.name = name;
		this.gender = gender;
		this.birth_date = birth_date;
		this.phase = phase;
		this.buildings = buildings;
		this.coins = coins;
		this.time_in_city = time_in_city;
		this.app_rating = app_rating;
		this.picture_url = picture_url;
		this.room = room;
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
	
	
	public Integer getTime_in_city() {
		return time_in_city;
	}

	public void setTime_in_city(Integer time_in_city) {
		this.time_in_city = time_in_city;
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
				+ birth_date + ", phase=" + phase + ", buildings=" + buildings
				+ ", coins=" + coins + ", time_in_city=" + time_in_city
				+ ", app_rating=" + app_rating + ", picture_url=" + picture_url
				+ ", room=" + room + "]";
	}
}
