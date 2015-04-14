package br.com.motogatomanager.modelo;

public class ViewedStudent extends Generic {
	private Student student;
	private Teacher teacher;
	private School school;
	
	public ViewedStudent() {
		super();
	}

	public ViewedStudent(Student student, Teacher teacher, School school) {
		super();
		this.student = student;
		this.teacher = teacher;
		this.school = school;
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

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Override
	public String toString() {
		return super.toString() + "ViewedStudent [student=" + student + ", teacher=" + teacher
				+ ", school=" + school + "]";
	}
	
	
	
}
