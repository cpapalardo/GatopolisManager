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
import javax.persistence.Transient;

@Entity
@Table(name="room")
@NamedQuery(name="Group.findByInepCode", query="select r from Room r WHERE r.teacher.school.schoolData.inep = :inep")
public class Room extends JsonBehaviour implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false, length=45)
	private String name;
	
	@Column(nullable=false, length=45)
	private String serie;
	
	@Column(nullable=false)
	private Character term;
	
	@ManyToOne
	@JoinColumn(name="teacher_id")
	private Teacher teacher;
	
	@Transient
	private Integer qtdeAlunos;
	
	public Room(){}
	
	public Room(Integer id, String name, String serie, Character term,
			Teacher teacher, Integer qtdeAlunos) {
		super();
		this.id = id;
		this.name = name;
		this.serie = serie;
		this.term = term;
		this.teacher = teacher;
		this.qtdeAlunos = qtdeAlunos;
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

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Character getTerm() {
		return term;
	}

	public void setTerm(Character term) {
		this.term = term;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Integer getQtdeAlunos() {
		return qtdeAlunos;
	}

	public void setQtdeAlunos(Integer qtdeAlunos) {
		this.qtdeAlunos = qtdeAlunos;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", serie=" + serie
				+ ", term=" + term + ", teacher=" + teacher.getId()
				+ ", qtdeAlunos=" + qtdeAlunos + "]";
	}
	
}
