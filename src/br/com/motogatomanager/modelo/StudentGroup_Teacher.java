package br.com.motogatomanager.modelo;

public class StudentGroup_Teacher extends Generic {
	private School school;
	private StudentGroup student_group_id;
	private Teacher teacher_id;
	
	public StudentGroup_Teacher() {}
	
	public StudentGroup_Teacher(School school, StudentGroup student_group_id, Teacher teacher_id) {
		this.school = school;
		this.student_group_id = student_group_id;
		this.teacher_id = teacher_id;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public StudentGroup getStudent_group_id() {
		return student_group_id;
	}

	public void setStudent_group_id(StudentGroup student_group_id) {
		this.student_group_id = student_group_id;
	}

	public Teacher getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(Teacher teacher_id) {
		this.teacher_id = teacher_id;
	}

	@Override
	public String toString() {
		return super.toString() + "StudentGroup_Teacher [school=" + school + ", student_group_id="
				+ student_group_id + ", teacher_id=" + teacher_id + "]";
	}

	
	
}
