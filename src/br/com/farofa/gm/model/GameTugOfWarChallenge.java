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
	private String name;
	
	@Column(name="total_correct", nullable=false)
	private Integer totalCorrect;
	
	@Column(name="total_played", nullable=false)
	private Integer totalPlayed;
	
	@Column(name="consecutive_full_team", nullable=false)
	private Integer consecutiveFullTeam;
	
	@ManyToOne()
	@JoinColumn(name="student_id", nullable=false)
	private Student student;

	public GameTugOfWarChallenge(){
		super();
	}
	
	public GameTugOfWarChallenge(String json){
		setJson(json);
	}
	
	public GameTugOfWarChallenge(Integer id, Boolean isDeleted, String name, Integer totalCorrect, Integer timesPlayed,
			Integer consecutiveFullTeam, Student student) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.name = name;
		this.totalCorrect = totalCorrect;
		this.totalPlayed = timesPlayed;
		this.consecutiveFullTeam = consecutiveFullTeam;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalCorrect() {
		return totalCorrect;
	}

	public void setTotalCorrect(Integer totalCorrect) {
		this.totalCorrect = totalCorrect;
	}

	public Integer getTimesPlayed() {
		return totalPlayed;
	}

	public void setTimesPlayed(Integer timesPlayed) {
		this.totalPlayed = timesPlayed;
	}

	public Integer getConsecutiveFullTeam() {
		return consecutiveFullTeam;
	}

	public void setConsecutiveFullTeam(Integer consecutiveFullTeam) {
		this.consecutiveFullTeam = consecutiveFullTeam;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "GameTugOfWarChallenge [id=" + id + ", isDeleted=" + isDeleted + ", name=" + name + ", totalCorrect="
				+ totalCorrect + ", timesPlayed=" + totalPlayed + ", consecutiveFullTeam=" + consecutiveFullTeam
				+ ", student=" + student + "]";
	}
	
	
}
