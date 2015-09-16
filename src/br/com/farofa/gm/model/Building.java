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

@SuppressWarnings("serial")
@Entity
@Table(name = "building")
@NamedQuery(name="Building.findByInepCode", query="select b from Building b WHERE b.student.room.teacher.school.schoolData.inep = :inep and b.isDeleted = false")
public class Building extends JsonBehaviour implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="is_deleted", nullable=false)
	private Boolean isDeleted;
	
	@Column(nullable=false, length=50)
	private String name;
	
	@Column(nullable=false)
	private Integer positionX;
	
	@Column(nullable=true)
	private Integer positionY;
	
	@ManyToOne
	@JoinColumn(name="student_id", nullable=false)
	private Student student;
	
	public Building() {}
	
	public Building(String json) {
		super.setJson(json);
	}
	
	public Building(String name, Integer positionX,
			Integer positionY, Student student) {
		super();
		this.name = name;
		this.positionX = positionX;
		this.positionY = positionY;
		this.student = student;
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
		return super.toString() + "Buildings [name=" + name + ", positionX="
				+ positionX + ", positionY=" + positionY + ", student="
				+ (student != null ? student.getId() : 0) + "]";
	}
}
