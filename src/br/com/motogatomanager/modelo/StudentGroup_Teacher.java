package br.com.motogatomanager.modelo;

public class StudentGroup_Teacher extends Generic {
	private School school;
	private StudentGroup studentGroup;
	private Teacher teacher;
	
	public StudentGroup_Teacher() {}
	
	public StudentGroup_Teacher(School school, StudentGroup studentGroup, Teacher teacher) {
		this.school = school;
		this.studentGroup = studentGroup;
		this.teacher = teacher;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public StudentGroup getStudentGroup() {
		return studentGroup;
	}

	public void setStudentGroup(StudentGroup studentGroup) {
		this.studentGroup = studentGroup;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher_id(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return super.toString() + "StudentGroup_Teacher [school=" + school + ", student_group_id="
				+ studentGroup + ", teacher_id=" + teacher + "]";
	}

	
	
}
