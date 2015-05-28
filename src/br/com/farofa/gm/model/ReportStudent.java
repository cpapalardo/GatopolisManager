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
@Table(name="report_student")
@NamedQuery(name="ReportStudent.findByInepCode", query="select rs from ReportStudent rs WHERE rs.student.group.teacher.school.schoolData.inep = :inep")
public class ReportStudent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true)
	private Date date_accessed;
	
	@Column(nullable=true)
	private Integer view_count;
	
	@ManyToOne
	@JoinColumn(name="teacher_view", nullable=true)
	private Teacher teacher_view;
	
	@ManyToOne
	@JoinColumn(name="student_id", nullable=false)
	private Student student;
	
	public ReportStudent() {
		super();
	}

	public ReportStudent(Integer id, Date date_accessed, Integer view_count,
			Teacher teacher_view, Student student) {
		super();
		this.id = id;
		this.date_accessed = date_accessed;
		this.view_count = view_count;
		this.teacher_view = teacher_view;
		this.student = student;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate_accessed() {
		return date_accessed;
	}

	public void setDate_accessed(Date date_accessed) {
		this.date_accessed = date_accessed;
	}

	public Integer getView_count() {
		return view_count;
	}

	public void setView_count(Integer view_count) {
		this.view_count = view_count;
	}

	public Teacher getTeacher_view() {
		return teacher_view;
	}

	public void setTeacher_view(Teacher teacher_view) {
		this.teacher_view = teacher_view;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
