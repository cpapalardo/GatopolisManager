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
@Table(name="report_student")
@NamedQuery(name="ReportStudent.findByInepCode", query="select rs from ReportStudent rs WHERE rs.student.room.teacher.school.schoolData.inep = :inep and rs.isDeleted = false")
public class ReportStudent extends JsonBehaviour implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="is_deleted", nullable=false)
	private Boolean isDeleted;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true, name="date_access")
	private Date date_accessed;
	
	@Column(nullable=true)
	private Integer view_count;
	
	@ManyToOne
	@JoinColumn(name="teacher_view", nullable=true)
	private Teacher teacher_view;
	
	@ManyToOne
	@JoinColumn(name="student_id", nullable=false)
	private Student student;
	
	public ReportStudent() {}
	
	public ReportStudent(String json) {
		super.setJson(json);
	}

	public ReportStudent(Integer id, Date date_accessed, Integer view_count,
			Teacher teacher_view, Student student) {
		super();
		this.date_accessed = date_accessed;
		this.view_count = view_count;
		this.teacher_view = teacher_view;
		this.student = student;
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
		return super.toString() + "ReportStudent [date_accessed=" + date_accessed
				+ ", view_count=" + view_count + ", teacher_view="
				+ teacher_view.getId() + ", student=" + student.getId() + "]";
	}
}
