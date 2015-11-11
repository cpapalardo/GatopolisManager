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
	
	@Column(name="total_correct", nullable=true)
	private Integer totalCorrect;
	
	@Column(name="total_played", nullable=true)
	private Integer totalPlayed;
	
	@Column(nullable = false)
	private Integer level;
	
	@Column(name="percentage_correct", nullable=false)
	private Float percentageCorrect;
	
	@ManyToOne()
	@JoinColumn(name="student_id", nullable=false)
	private Student student;

	public GameTugOfWarChallenge(){
		super();
	}
	
	public GameTugOfWarChallenge(String json){
		setJson(json);
	}
	
	public GameTugOfWarChallenge(Integer id, Boolean isDeleted, Integer phase, Integer totalCorrect, Integer timesPlayed,
			Integer consecutiveFullTeam, Student student) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.phase = phase;
		this.totalCorrect = totalCorrect;
		this.totalPlayed = timesPlayed;
		this.student = student;
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

	public Integer getTotalCorrect() {
		return totalCorrect;
	}

	public void setTotalCorrect(Integer totalCorrect) {
		this.totalCorrect = totalCorrect;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getTotalPlayed() {
		return totalPlayed;
	}

	public void setTotalPlayed(Integer totalPlayed) {
		this.totalPlayed = totalPlayed;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Float getPercentageCorrect() {
		return percentageCorrect;
	}

	public void setPercentageCorrect(Float percentageCorrect) {
		this.percentageCorrect = percentageCorrect;
	}

	@Override
	public String toString() {
		return "GameTugOfWarChallenge [id=" + id + ", isDeleted=" + isDeleted + ", phase=" + phase + ", totalCorrect="
				+ totalCorrect + ", totalPlayed=" + totalPlayed + ", level=" + level + ", percentageCorrect="
				+ percentageCorrect + ", student=" + student + "]";
	}	
	
}
