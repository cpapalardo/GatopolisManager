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
@Table(name = "transition")
@NamedQuery(name="Transition.findByInepCode", query="select t from Transition t WHERE t.student.group.teacher.school.schoolData.inep = :inep")
public class Transition extends JsonBehaviour implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer Id;
	
	@Column(nullable=false, length=45)
	private String diagnosis_level;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date created_at;
	
	@ManyToOne
	@JoinColumn(name="student_id", nullable=false)
	private Student student;
	
	public Transition () {}

	public Transition(Integer id, String diagnosis_level, Date created_at,
			Student student) {
		super();
		Id = id;
		this.diagnosis_level = diagnosis_level;
		this.created_at = created_at;
		this.student = student;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
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

	@Override
	public String toString() {
		return "Transition [Id=" + Id + ", diagnosis_level=" + diagnosis_level
				+ ", created_at=" + created_at + ", student=" + student.getId() + "]";
	}
	
}
