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
@Table(name = "game_tug_of_war_challenge")
@NamedQuery(name = "GameTugOfWarChallenge.findByInepCode", query = "select towc from GameTugOfWarChallenge towc WHERE towc.student.room.teacher.school.schoolData.inep = :inep and towc.isDeleted = false")
public class GameTugOfWarChallenge extends JsonBehaviour implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "is_deleted", nullable = false)
	private Boolean isDeleted;

	@Column(name = "percentage_correct", nullable = false)
	private Float percentageCorrect;

	@ManyToOne()
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "created_at")
	private Date createdAt;

	@Column(name = "name_challenge", nullable = false)
	private Integer nameChallenge;

	@Column(nullable = false, name = "current_phase")
	private Integer currentPhase;

	public GameTugOfWarChallenge() {
		super();
	}

	public GameTugOfWarChallenge(String json) {
		setJson(json);
	}

	public GameTugOfWarChallenge(Integer id, Boolean isDeleted, Date createdAt, Student student, Integer name_challenge,
			Integer current_phase) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.student = student;
		this.createdAt = createdAt;
		this.nameChallenge = name_challenge;
		this.currentPhase = current_phase;
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Float getPercentageCorrect() {
		return percentageCorrect;
	}

	public void setPercentageCorrect(Float percentageCorrect) {
		this.percentageCorrect = percentageCorrect;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "GameTugOfWarChallenge [id=" + id + ", isDeleted=" + isDeleted + ", percentageCorrect="
				+ percentageCorrect + ", student=" + student + ", createdAt=" + createdAt + "nameChallenge="
				+ nameChallenge + "currenta_phase=" + currentPhase + "]";
	}
}
