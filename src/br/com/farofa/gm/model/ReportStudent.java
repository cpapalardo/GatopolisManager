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

import org.json.JSONObject;

@Entity
@Table(name="report_student")
@NamedQuery(name="ReportStudent.findByInepCode", query="select rs from ReportStudent rs WHERE rs.student.room.teacher.school.schoolData.inep = :inep")
public class ReportStudent implements Serializable, JsonBehaviour {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
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

	@Override
	public String toString() {
		return "ReportStudent [id=" + id + ", date_accessed=" + date_accessed
				+ ", view_count=" + view_count + ", teacher_view="
				+ teacher_view.getId() + ", student=" + student.getId() + "]";
	}
	
	@Override
	public String getJson() {
		JSONObject jsonObj = new JSONObject();
		DateFormat df = new SimpleDateFormat("yy/MM/yyyy hh:mm:ss");
		if (id != null) jsonObj.put("id", id);
		if (date_accessed != null) jsonObj.put("date_accessed", df.format(date_accessed));
		if (view_count != null) jsonObj.put("view_count", view_count);
		if (teacher_view != null && teacher_view.getId() != null) jsonObj.put("teacher_view", teacher_view);
		if (student != null && student.getId() != null) jsonObj.put("student", student.getId());
		return jsonObj.toString();
	}

	@Override
	public void setJson(String json) {
		JSONObject jsonObj = new JSONObject(json);
		DateFormat df = new SimpleDateFormat("yy/MM/yyyy hh:mm:ss");
		if (jsonObj.has("id")) id = jsonObj.getInt("id");
		if (jsonObj.has("view_count")) view_count = jsonObj.getInt("view_count");
		if (jsonObj.has("date_accessed")) {
			try {
				String date = jsonObj.getString("date_accessed");
				date_accessed = df.parse(date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (jsonObj.has("teacher_view")) {
			teacher_view = new Teacher();
			int id = jsonObj.getInt("teacher_view");
			teacher_view.setId(id);
		}
		if (jsonObj.has("student")) {
			student = new Student();
			int id = jsonObj.getInt("student");
			student.setId(id);
		}
	}
	
}
