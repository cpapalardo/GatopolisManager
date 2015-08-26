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
@Table(name = "game_super_cat_challenge")
@NamedQuery(name="GameSuperCatChallenge.findByInepCode", query="select gscc from GameSuperCatChallenge gscc WHERE gscc.student.room.teacher.school.schoolData.inep = :inep and gscc.isDeleted = false")
public class GameSuperCatChallenge extends JsonBehaviour implements Serializable  {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="is_deleted", nullable=false)
	private Boolean isDeleted;
	
	@Column(nullable=false, length=100)
	private String name;
	
	@Column(nullable=true, name="total_correct")
	private Integer totalCorrect;
	
	@Column(nullable=true, name="total_played")
	private Integer totalPlayed;
	
	@ManyToOne()
	@JoinColumn(name="student_id", nullable=false)
	private Student student;

	public GameSuperCatChallenge() {
		super();
	}
	
	public GameSuperCatChallenge(String json) {
		setJson(json);
	}

	public GameSuperCatChallenge(Integer id, Boolean isDeleted, String name,
			Integer totalCorrect, Integer totalPlayed, Student student) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.name = name;
		this.totalCorrect = totalCorrect;
		this.totalPlayed = totalPlayed;
		this.student = student;
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
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getTotalCorrect() {
		return totalCorrect;
	}

	public void setTotalCorrect(Integer totalCorrect) {
		this.totalCorrect = totalCorrect;
	}

	public Integer getTotalPlayed() {
		return totalPlayed;
	}

	public void setTotalPlayed(Integer totalPlayed) {
		this.totalPlayed = totalPlayed;
	}
	
	
}
