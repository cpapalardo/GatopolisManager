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
@Table(name = "game_super_cat")
@NamedQuery(name="GameSuperCat.findByInepCode", query="select gsc from GameSuperCat gsc WHERE gsc.student.room.teacher.school.schoolData.inep = :inep and gsc.isDeleted = false")
public class GameSuperCat extends JsonBehaviour implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="is_deleted", nullable=false)
	private Boolean isDeleted;
	
	@Column(nullable=false, length=100)
	private String expected;
	
	@Column(nullable=false, length=100)
	private String input;
	
	@Column(nullable=false, length=50)
	private String phase;
	
	@Column(nullable=false, length=50, name="finger_code")
	private String fingerCode;
	
	@Column(nullable=false, name="time_wasted")
	private Integer timeWasted;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, name="created_at")
	private Date createdAt;
	
	@ManyToOne()
	@JoinColumn(name="student_id", nullable=false)
	private Student student;
	
	public GameSuperCat () {}
	
	public GameSuperCat (String json) {
		super.setJson(json);
	}

	public GameSuperCat(String expected, String input, String phase, Integer time_wasted,
			Date created_at, Student student) {
		super();
		this.expected = expected;
		this.input = input;
		this.phase = phase;
		this.timeWasted = time_wasted;
		this.createdAt = created_at;
		this.student = student;
	}

	public String getExpected() {
		return expected;
	}

	public void setExpected(String expected) {
		this.expected = expected;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public Date getCreated_at() {
		return createdAt;
	}

	public void setCreated_at(Date created_at) {
		this.createdAt = created_at;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getTime_wasted() {
		return timeWasted;
	}

	public void setTime_wasted(Integer time_wasted) {
		this.timeWasted = time_wasted;
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

	public String getFingerCode() {
		return fingerCode;
	}

	public void setFingerCode(String fingerCode) {
		this.fingerCode = fingerCode;
	}

	public Integer getTimeWasted() {
		return timeWasted;
	}

	public void setTimeWasted(Integer timeWasted) {
		this.timeWasted = timeWasted;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return super.toString() + "GameSuperCat [expected=" + expected + ", input=" + input
				+ ", phase=" + phase + ", time_wasted=" + timeWasted
				+ ", created_at=" + createdAt + ", student=" + student + "]";
	}
	
	
}
