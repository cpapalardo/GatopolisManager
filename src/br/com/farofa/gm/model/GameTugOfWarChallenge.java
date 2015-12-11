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
@NamedQuery(name="GameTugOfWarChallenge.findByInepCode", query="select towc from GameTugOfWarChallenge towc WHERE towc.student.room.teacher.school.schoolData.inep = :inep and towc.isDeleted = false")
public class GameTugOfWarChallenge extends JsonBehaviour implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="is_deleted", nullable=false)
	private Boolean isDeleted;
	
	@Column(nullable=false)
	private Integer phase;
	
	@Column(name="percentage_correct", nullable=false)
	private Float percentageCorrect;
	
	@ManyToOne()
	@JoinColumn(name="student_id", nullable=false)
	private Student student;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, name="created_at")
	private Date createdAt;

	public GameTugOfWarChallenge(){
		super();
	}
	
	public GameTugOfWarChallenge(String json){
		setJson(json);
	}
	
	public GameTugOfWarChallenge(Integer id, Boolean isDeleted, Integer phase, Integer totalCorrect, Integer timesPlayed, Date createdAt, Date modifiedAt,
			Integer consecutiveFullTeam, Student student, String name) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.phase = phase;
		this.student = student;
		this.createdAt = createdAt;
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

	public Integer getPhase() {
		return phase;
	}

	public void setPhase(Integer phase) {
		this.phase = phase;
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
		return "GameTugOfWarChallenge [id=" + id + ", isDeleted=" + isDeleted + ", phase=" + phase
				+ ", percentageCorrect=" + percentageCorrect + ", student=" + student + ", createdAt=" + createdAt;
	}


}
