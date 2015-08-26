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

@SuppressWarnings("serial")
@Entity
@Table(name = "game_bird")
@NamedQuery(name="GameBird.findByInepCode", query="select gb from GameBird gb WHERE gb.student.room.teacher.school.schoolData.inep = :inep and gb.isDeleted = false")
public class GameBird extends JsonBehaviour implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="is_deleted", nullable=false)
	private Boolean isDeleted;
	
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
	
	public GameBird(String json){
		super.setJson(json);
	}

	public GameBird(Boolean completed, Integer time_wasted, String letters_sequence,
			String letters_time, String letters_faults, Date played_date,
			Student student) {
		super();
		this.completed = completed;
		this.time_wasted = time_wasted;
		this.letters_sequence = letters_sequence;
		this.letters_time = letters_time;
		this.letters_faults = letters_faults;
		this.played_date = played_date;
		this.student = student;
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

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return super.toString() + "GameBird [completed=" + completed
				+ ", time_wasted=" + time_wasted + ", letter_sequence=" + letters_sequence + ", letters_time="
				+ letters_time + ", letters_faults=" + letters_faults
				+ ", played_date=" + df.format(played_date) + ", student=" + student.getId() + "]";
	}
}
