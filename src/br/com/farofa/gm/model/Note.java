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
@Table(name = "note")
@NamedQuery(name="Note.findByInepCode", query="select n from Note n WHERE n.student.room.teacher.school.schoolData.inep = :inep")
public class Note implements Serializable, JsonBehaviour {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
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
		this.id = id;
		this.note = note;
		this.created_at = created_at;
		this.teacher = teacher;
		this.student = student;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return "Note [Id=" + id + ", note=" + note + ", created_at="
				+ created_at + ", teacher=" + teacher + ", student=" + student.getId()
				+ "]";
	}
	
	@Override
	public String getJson() {
		JSONObject jsonObj = new JSONObject();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if (id != null) jsonObj.put("id", id);
		if (note != null) jsonObj.put("note", note);
		if (created_at != null) jsonObj.put("created_at", df.format(created_at));
		if (teacher != null && teacher.getId() != null) jsonObj.put("teacher", teacher.getId());
		if (student != null && student.getId() != null) jsonObj.put("student", student.getId());
		return jsonObj.toString();
	}

	@Override
	public void setJson(String json) {
		JSONObject jsonObj = new JSONObject(json);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if (jsonObj.has("id")) 
			if (jsonObj.getInt("id") != 0)
				id = jsonObj.getInt("id");
		if (jsonObj.has("note")) note = jsonObj.getString("note");
		if (jsonObj.has("created_at")) {
			try {
				String date = jsonObj.getString("created_at");
				created_at = df.parse(date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (jsonObj.has("teacher")) {
			teacher = new Teacher();
			int id = jsonObj.getInt("teacher");
			teacher.setId(id);
		}
		if (jsonObj.has("student")) {
			student = new Student();
			int id = jsonObj.getInt("student");
			student.setId(id);
		}
	}
}
