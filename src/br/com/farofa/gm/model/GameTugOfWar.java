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
@Table(name = "game_tug_of_war")
@NamedQuery(name="GameTugOfWar.findByInepCode", query="select tow from GameTugOfWar tow WHERE tow.student.room.teacher.school.schoolData.inep = :inep and tow.isDeleted = false")
public class GameTugOfWar extends JsonBehaviour implements Serializable{
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="is_deleted", nullable=false)
	private Boolean isDeleted;
		
	@Column(name="name_challenge", nullable=false)
	private Integer nameChallenge;
	
	@ManyToOne()
	@JoinColumn(name="student_id", nullable=false)
	private Student student;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, name="created_at")
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, name="modified_at")
	private Date modifiedAt;
	
	@Column(nullable=false, name="time_wasted")
	private Integer timeWasted;
	
	@Column(nullable=false, name="current_phase")
	private Integer currentPhase;
	
	@Column(nullable=true, name="win_streak")
	private Integer winStreak;
	
	public GameTugOfWar(){
		super();
	}
	
	public GameTugOfWar(String json){
		super.setJson(json);
	}

	public GameTugOfWar(Integer id, Boolean isDeleted, Integer name_challenge, Integer level, Date createdAt, Date modifiedAt, Integer time_wasted,
			Student student, Integer current_phase, Integer win_streak) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.nameChallenge = name_challenge;
		this.student = student;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.timeWasted = time_wasted;
		this.currentPhase = current_phase;
		this.winStreak = win_streak;
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

	public Integer getName_challenge() {
		return nameChallenge;
	}

	public void setName_is_correct(Integer nameChallenge) {
		this.nameChallenge = nameChallenge;
	}


	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	

	public Integer getNameChallenge() {
		return nameChallenge;
	}

	public void setNameChallenge(Integer nameChallenge) {
		this.nameChallenge = nameChallenge;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Integer getTimeWasted() {
		return timeWasted;
	}

	public void setTimeWasted(Integer timeWasted) {
		this.timeWasted = timeWasted;
	}

	public Integer getCurrentPhase() {
		return currentPhase;
	}

	public void setCurrentPhase(Integer currentPhase) {
		this.currentPhase = currentPhase;
	}

	public Integer getWinStreak() {
		return winStreak;
	}

	public void setWinStreak(Integer winStreak) {
		this.winStreak = winStreak;
	}

	@Override
	public String toString() {
		return "GameTugOfWar [id=" + id + ", isDeleted=" + isDeleted + ",nameChallenge="
				+ nameChallenge + ", student=" + student + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt
				+ ", timeWasted=" + timeWasted + "zcurrentPhase=" + currentPhase + "winStreak="+ winStreak +"]";
	}
	
}
