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

import org.json.JSONObject;

@Entity
@Table(name="student")
@NamedQuery(name="Student.findByInepCode", query="select s from Student s WHERE s.room.teacher.school.schoolData.inep = :inep")
public class Student implements Serializable, JsonBehaviour {
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
	
	public Student () {}

	public Student(Integer id, String name, Character gender, Date birth_date,
			Integer buildings, Integer coins, Integer time_in_city,
			Integer app_rating, String picture_url, Room room) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birth_date = birth_date;
		this.buildings = buildings;
		this.coins = coins;
		this.time_in_city = time_in_city;
		this.app_rating = app_rating;
		this.picture_url = picture_url;
		this.room = room;
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

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return "Student [id=" + id + ", name=" + name + ", gender=" + gender
				+ ", birth_date=" + df.format(birth_date) + ", buildings=" + buildings
				+ ", coins=" + coins + ", time_in_city=" + time_in_city
				+ ", app_rating=" + app_rating + ", picture_url=" + picture_url
				+ ", room=" + room.getId() + "]";
	}
	
	@Override
	public String getJson() {
		JSONObject jsonObj = new JSONObject();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if (id != null) jsonObj.put("id", id);
		if (name != null) jsonObj.put("name", name);
		if (gender != null) jsonObj.put("gender", gender);
		if (birth_date != null) jsonObj.put("birth_date", df.format(birth_date));
		if (buildings != null) jsonObj.put("buildings", buildings);
		if (coins != null) jsonObj.put("coins", coins);
		if (time_in_city != null) jsonObj.put("time_in_city", time_in_city);
		if (app_rating != null) jsonObj.put("app_rating", app_rating);
		if (picture_url != null) jsonObj.put("picture_url", picture_url);
		if (room != null && room.getId() != null) jsonObj.put("room", room.getId());
		return jsonObj.toString();
	}

	@Override
	public void setJson(String json) {
		JSONObject jsonObj = new JSONObject(json);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if (jsonObj.has("id"))
			if (jsonObj.getInt("id") != 0)
				id = jsonObj.getInt("id");
		if (jsonObj.has("name")) name = jsonObj.getString("name");
		if (jsonObj.has("gender")) gender = jsonObj.getString("gender").charAt(0);
		if (jsonObj.has("birth_date")) {
			try {
				String date = jsonObj.getString("birth_date");
				birth_date = df.parse(date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (jsonObj.has("buildings")) buildings = jsonObj.getInt("buildings");
		if (jsonObj.has("coins")) coins = jsonObj.getInt("coins");
		if (jsonObj.has("time_in_city")) time_in_city = jsonObj.getInt("time_in_city");
		if (jsonObj.has("app_rating")) app_rating = jsonObj.getInt("app_rating");
		if (jsonObj.has("picture_url")) picture_url = jsonObj.getString("picture_url");
		if (jsonObj.has("room")) {
			room = new Room();
			int id = jsonObj.getInt("room");
			room.setId(id);
		}
	}

	
}
