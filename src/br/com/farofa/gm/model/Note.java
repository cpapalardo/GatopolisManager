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
@Table(name = "note")
@NamedQuery(name="Note.findByInepCode", query="select n from Note n WHERE n.student.group.teacher.school.schoolData.inep = :inep")
public class Note extends JsonBehaviour implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer Id;
	
	@Column(nullable=false, length=255)
	private String note;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date created_at;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	@ManyToOne
	@JoinColumn(name="student_id", nullable=false)
	private Student student;
	
	public Note () {}


	public Note(Integer id, String note, Date created_at, Teacher teacher,
			Student student) {
		super();
		Id = id;
		this.note = note;
		this.created_at = created_at;
		this.teacher = teacher;
		this.student = student;
	}


	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Note [Id=" + Id + ", note=" + note + ", created_at="
				+ created_at + ", teacher=" + teacher + ", student=" + student.getId()
				+ "]";
	}
	
}
