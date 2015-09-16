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
	private Date dateAccess;
	
	@Column(nullable=true, name="view_count")
	private Integer viewCount;
	
	@ManyToOne
	@JoinColumn(nullable=true, name="teacher_view")
	private Teacher teacherView;
	
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
		this.dateAccess = date_accessed;
		this.viewCount = view_count;
		this.teacherView = teacher_view;
		this.student = student;
	}

	public Date getDate_accessed() {
		return dateAccess;
	}

	public void setDate_accessed(Date date_accessed) {
		this.dateAccess = date_accessed;
	}

	public Integer getView_count() {
		return viewCount;
	}

	public void setView_count(Integer view_count) {
		this.viewCount = view_count;
	}

	public Teacher getTeacher_view() {
		return teacherView;
	}

	public void setTeacher_view(Teacher teacher_view) {
		this.teacherView = teacher_view;
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
		return super.toString() + "ReportStudent [date_accessed=" + dateAccess
				+ ", view_count=" + viewCount + ", teacher_view="
				+ teacherView.getId() + ", student=" + student.getId() + "]";
	}
}
