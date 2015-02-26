package br.com.motogatomanager.modelo;

import java.util.Date;


public class Student extends Generic {
	private String name;
	private String last_name;
	private String gender;
	private Date birth_date;
	private String guardian;
	private String diagnosis_level;
	private int coins;
	private int buildings_count;
	
	private School school;
	private StudentGroup student_group;
	
	public Student () {}
	
	public Student(String name, String last_name, String gender,
			Date birth_date, String guardian, String diagnosis_level, int coins,
			int buildings_count, School school, StudentGroup student_group) {
		this.name = name;
		this.last_name = last_name;
		this.gender = gender;
		this.birth_date = birth_date;
		this.guardian = guardian;
		this.diagnosis_level = diagnosis_level;
		this.coins = coins;
		this.buildings_count = buildings_count;
		this.school = school;
		this.student_group = student_group;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public String getGuardian() {
		return guardian;
	}
	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}
	public String getDiagnosis_level() {
		return diagnosis_level;
	}
	public void setDiagnosis_level(String diagnosis_level) {
		this.diagnosis_level = diagnosis_level;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public int getBuildings_count() {
		return buildings_count;
	}
	public void setBuildings_count(int buildings_count) {
		this.buildings_count = buildings_count;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public StudentGroup getStudent_group() {
		return student_group;
	}
	public void setStudent_group(StudentGroup student_group) {
		this.student_group = student_group;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", last_name=" + last_name
				+ ", gender=" + gender + ", birth_date=" + birth_date
				+ ", guardian=" + guardian + ", diagnosis_level="
				+ diagnosis_level + ", coins=" + coins + ", buildings_count="
				+ buildings_count + ", school=" + school + ", student_group="
				+ student_group + ", getId()=" + getId() + ", getCreatedAt()="
				+ getCreatedAt() + ", getUpdatedAt()=" + getUpdatedAt()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
