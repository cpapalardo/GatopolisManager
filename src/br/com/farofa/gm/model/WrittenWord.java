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

@Entity
@Table(name = "written_word")
@NamedQuery(name="WrittenWord.findByInepCode", query="select ww from WrittenWord ww WHERE ww.student.group.teacher.school.schoolData.inep = :inep")
public class WrittenWord implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int Id;
	
	@Column(nullable=false, length=45)
	private String word;
	
	@Column(nullable=false, length=45)
	private String input;
	
	@Column(nullable=false, length=255)
	private String gesture;
	
	@Column(nullable=false, length=45)
	private String diagnosis_level;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date created_at;
	
	@ManyToOne
	@JoinColumn(name="student_id", nullable=false)
	private Student student;
	
	public WrittenWord () {}
	
	public WrittenWord(int id, String word, String input, String gesture,
			String diagnosis_level, Date created_at, Student student) {
		super();
		Id = id;
		this.word = word;
		this.input = input;
		this.gesture = gesture;
		this.diagnosis_level = diagnosis_level;
		this.created_at = created_at;
		this.student = student;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getGesture() {
		return gesture;
	}

	public void setGesture(String gesture) {
		this.gesture = gesture;
	}

	public String getDiagnosis_level() {
		return diagnosis_level;
	}

	public void setDiagnosis_level(String diagnosis_level) {
		this.diagnosis_level = diagnosis_level;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
	
}
