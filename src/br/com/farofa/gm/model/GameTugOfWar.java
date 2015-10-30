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
	
	@Column(name="audio_name", nullable=false)
	private String audioName;
	
	@Column(name="name_is_correct", nullable=false)
	private Boolean nameIsCorrect;
	
	@Column(nullable=false)
	private Integer level;
	
	@ManyToOne()
	@JoinColumn(name="student_id", nullable=false)
	private Student student;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, name="created_at")
	private Date createdAt;
	
	public GameTugOfWar(){
		super();
	}
	
	public GameTugOfWar(String json){
		super.setJson(json);
	}

	public GameTugOfWar(Integer id, Boolean isDeleted, String audio_name, Boolean name_is_correct, Integer level, Date createdAt,
			Student student) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.audioName = audio_name;
		this.nameIsCorrect = name_is_correct;
		this.level = level;
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

	public String getAudio_name() {
		return audioName;
	}

	public void setAudio_name(String audio_name) {
		this.audioName = audio_name;
	}

	public Boolean getName_is_correct() {
		return nameIsCorrect;
	}

	public void setName_is_correct(Boolean name_is_correct) {
		this.nameIsCorrect = name_is_correct;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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

	@Override
	public String toString() {
		return "GameTugOfWar [id=" + id + ", isDeleted=" + isDeleted + ", audio_name=" + audioName
				+ ", name_is_correct=" + nameIsCorrect + ", level=" + level + ", student=" + student + "]";
	}

}
