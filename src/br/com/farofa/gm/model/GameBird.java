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
@Table(name = "game_bird")
@NamedQuery(name="GameBird.findByInepCode", query="select gb from GameBird gb WHERE gb.student.room.teacher.school.schoolData.inep = :inep")
public class GameBird implements Serializable, JsonBehaviour {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false)
	private Boolean completed;
	
	@Column(nullable=false, length=255)
	private Integer time_wasted;
	
	@Column(nullable=false, length=45)
	private String letters_sequence;
	
	@Column(nullable=false, length=255)
	private String letters_time;
	
	@Column(nullable=false, length=255)
	private String letters_faults;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, name="played_date")
	private Date played_date;
	
	@ManyToOne
	@JoinColumn(name="student_id", nullable=false)
	private Student student;
	
	public GameBird(){}

	public GameBird(Integer id, Boolean completed, Integer time_wasted, String letters_sequence,
			String letters_time, String letters_faults, Date played_date,
			Student student) {
		super();
		this.id = id;
		this.completed = completed;
		this.time_wasted = time_wasted;
		this.letters_sequence = letters_sequence;
		this.letters_time = letters_time;
		this.letters_faults = letters_faults;
		this.played_date = played_date;
		this.student = student;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Integer getTime_wasted() {
		return time_wasted;
	}

	public void setTime_wasted(Integer time_wasted) {
		this.time_wasted = time_wasted;
	}

	public String getLetters_time() {
		return letters_time;
	}

	public void setLetters_time(String letters_time) {
		this.letters_time = letters_time;
	}

	public String getLetters_faults() {
		return letters_faults;
	}

	public void setLetters_faults(String letters_faults) {
		this.letters_faults = letters_faults;
	}

	public Date getPlayed_date() {
		return played_date;
	}

	public void setPlayed_date(Date played_date) {
		this.played_date = played_date;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getLetters_sequence() {
		return letters_sequence;
	}

	public void setLetters_sequence(String letters_sequence) {
		this.letters_sequence = letters_sequence;
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return "GameBird [id=" + id + ", completed=" + completed
				+ ", time_wasted=" + time_wasted + ", letter_sequence=" + letters_sequence + ", letters_time="
				+ letters_time + ", letters_faults=" + letters_faults
				+ ", played_date=" + df.format(played_date) + ", student=" + student.getId() + "]";
	}
	
	@Override
	public String getJson() {
		JSONObject jsonObj = new JSONObject();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if (id != null) jsonObj.put("id", id);
		if (completed != null) jsonObj.put("completed", completed);
		if (time_wasted != null) jsonObj.put("time_wasted", time_wasted);
		if (letters_sequence != null) jsonObj.put("letters_sequence", letters_sequence);
		if (letters_time != null) jsonObj.put("letters_time", letters_time);
		if (letters_faults != null) jsonObj.put("letters_faults", letters_faults);
		if (played_date != null) jsonObj.put("played_date", df.format(played_date));
		if (student != null && student.getId() != null) jsonObj.put("student", student.getId());
		return jsonObj.toString();
	}

	@Override
	public void setJson(String json) {
		JSONObject jsonObj = new JSONObject(json);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if (jsonObj.has("id")) 
			if (jsonObj.getInt("id") != 0)
				id = jsonObj.getInt("id");
		if (jsonObj.has("completed")) completed = jsonObj.getBoolean("completed");
		if (jsonObj.has("time_wasted")) time_wasted = jsonObj.getInt("time_wasted");
		if (jsonObj.has("letters_sequence")) letters_sequence = jsonObj.getString("letters_sequence");
		if (jsonObj.has("letters_time")) letters_time = jsonObj.getString("letters_time");
		if (jsonObj.has("letters_faults")) letters_faults = jsonObj.getString("letters_faults");
		if (jsonObj.has("played_date")) {
			try {
				String date = jsonObj.getString("played_date");
				played_date = df.parse(date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (jsonObj.has("student")) {
			student = new Student();
			int id = jsonObj.getInt("student");
			student.setId(id);
		}
	}
}
