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
@Table(name = "transition")
@NamedQuery(name="Transition.findByInepCode", query="select t from Transition t WHERE t.student.room.teacher.school.schoolData.inep = :inep and t.isDeleted = false")
public class Transition extends JsonBehaviour implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="is_deleted", nullable=false)
	private Boolean isDeleted;
	
	@Column(nullable=false, length=45)
	private String phase;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, name="created_at")
	private Date createdAt;
	
	@ManyToOne
	@JoinColumn(name="student_id", nullable=false)
	private Student student;

	public Transition() {
		super();
	}
	
	public Transition(String json) {
		setJson(json);
	}

	public Transition(Integer id, Boolean isDeleted, String phase,
			Date createdAt, Student student) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.phase = phase;
		this.createdAt = createdAt;
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

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Transition [id=" + id + ", isDeleted=" + isDeleted + ", phase="
				+ phase + ", createdAt=" + createdAt + ", student=" + student
				+ "]";
	}
}
