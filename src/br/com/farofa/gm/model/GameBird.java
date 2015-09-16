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
	
	@Column(nullable=false, name="time_wasted")
	private Integer timeWasted;
	
	@Column(nullable=false, length=50, name="letters_sequence")
	private String lettersSequence;
	
	@Column(nullable=false, length=255, name="letters_time")
	private String lettersTime;
	
	@Column(nullable=false, length=255, name="letters_faults")
	private String lettersFaults;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, name="played_date")
	private Date playedDate;
	
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
		this.timeWasted = time_wasted;
		this.lettersSequence = letters_sequence;
		this.lettersTime = letters_time;
		this.lettersFaults = letters_faults;
		this.playedDate = played_date;
		this.student = student;
	}
	
	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Integer getTime_wasted() {
		return timeWasted;
	}

	public void setTime_wasted(Integer time_wasted) {
		this.timeWasted = time_wasted;
	}

	public String getLetters_time() {
		return lettersTime;
	}

	public void setLetters_time(String letters_time) {
		this.lettersTime = letters_time;
	}

	public String getLetters_faults() {
		return lettersFaults;
	}

	public void setLetters_faults(String letters_faults) {
		this.lettersFaults = letters_faults;
	}

	public Date getPlayed_date() {
		return playedDate;
	}

	public void setPlayed_date(Date played_date) {
		this.playedDate = played_date;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getLetters_sequence() {
		return lettersSequence;
	}

	public void setLetters_sequence(String letters_sequence) {
		this.lettersSequence = letters_sequence;
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
				+ ", time_wasted=" + timeWasted + ", letter_sequence=" + lettersSequence + ", letters_time="
				+ lettersTime + ", letters_faults=" + lettersFaults
				+ ", played_date=" + df.format(playedDate) + ", student=" + student.getId() + "]";
	}
}
