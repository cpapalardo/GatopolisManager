package br.com.farofa.gm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name = "teacher")
@NamedQuery(name="Buildings.findByInepCode", query="select b from Buildings b WHERE b.student.room.teacher.school.schoolData.inep = :inep")
public class Buildings implements Serializable, JsonBehaviour {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false, length=45)
	private String name;
	
	@Column(nullable=false)
	private Integer positionX;
	
	@Column(nullable=false)
	private Integer positionY;
	
	@ManyToOne
	@JoinColumn(name="student_id", nullable=false)
	private Student student;
	
	public Buildings() {}
	
	public Buildings(Integer id, String name, Integer positionX,
			Integer positionY, Student student) {
		super();
		this.id = id;
		this.name = name;
		this.positionX = positionX;
		this.positionY = positionY;
		this.student = student;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPositionX() {
		return positionX;
	}

	public void setPositionX(Integer positionX) {
		this.positionX = positionX;
	}

	public Integer getPositionY() {
		return positionY;
	}

	public void setPositionY(Integer positionY) {
		this.positionY = positionY;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	@Override
	public String toString() {
		return "Buildings [id=" + id + ", name=" + name + ", positionX="
				+ positionX + ", positionY=" + positionY + ", student="
				+ (student != null ? student.getId() : 0) + "]";
	}

	@Override
	public String getJson() {
		JSONObject jsonObj = new JSONObject();
		if (id != null) jsonObj.put("id", id);
		if (name != null) jsonObj.put("name", name);
		if (positionX != null) jsonObj.put("positionX", positionX);
		if (positionY != null) jsonObj.put("positionY", positionY);
		if (student != null && student.getId() != null) jsonObj.put("student", student.getId());
		return jsonObj.toString();
	}

	@Override
	public void setJson(String json) {
		JSONObject jsonObj = new JSONObject(json);
		if (jsonObj.has("id")) id = jsonObj.getInt("id");
		if (jsonObj.has("name")) name = jsonObj.getString("name");
		if (jsonObj.has("positionX")) positionX = jsonObj.getInt("positionX");
		if (jsonObj.has("positionY")) positionY = jsonObj.getInt("positionY");
		if (jsonObj.has("student")) {
			student = new Student();
			int id = jsonObj.getInt("student");
			student.setId(id);
		}
	}
}
