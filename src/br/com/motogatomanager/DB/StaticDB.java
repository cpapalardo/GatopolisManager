package br.com.motogatomanager.db;

import java.util.ArrayList;
import java.util.List;

import br.com.motogatomanager.modelo.Group;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.Student;
import br.com.motogatomanager.modelo.Teacher;

public class StaticDB {
	public static List<School> SCHOOLS;
	public static List<Teacher> TEACHERS;
	public static List<Group> GROUPS;
	public static List<Student> STUDENTS;

	public static void instantiate() {
		School school = null;
		Teacher teacher1 = null;
		Teacher teacher2 = null;
		Group group = null;
		Student student = null;

		if (StaticDB.SCHOOLS == null) {
			StaticDB.SCHOOLS = new ArrayList<School>();
			school = new School("1234", "Escola Teste", "1", "1");
			school.setObjectId("x4iv8ky");
			StaticDB.SCHOOLS.add(school);
		}

		if (StaticDB.TEACHERS == null) {
			StaticDB.TEACHERS = new ArrayList<Teacher>();
			teacher1 = new Teacher("Professor", "Xavier", "1234",
					"email@email.com", true, "question", "answer", school,
					new byte[3]);
			teacher1.setObjectId("192j3u4");
			teacher2 = new Teacher("Professor", "Yield", "1234",
					"asdf@email.com", true, "question", "answer", school,
					new byte[3]);
			teacher2.setObjectId("12reyqr4");
			StaticDB.TEACHERS.add(teacher1);
			StaticDB.TEACHERS.add(teacher2);
		}

		if (StaticDB.GROUPS == null) {
			StaticDB.GROUPS = new ArrayList<Group>();
			group = new Group("Turma Marota", "1º ano A", "Manhã", null, school);
			group.setObjectId("1j902j3h");
			StaticDB.GROUPS.add(group);
		}

		if (StaticDB.STUDENTS == null) {
			StaticDB.STUDENTS = new ArrayList<Student>();
			student = new Student("Aluno", "Sobrenome", "M", 1, "Pai", "xxx",
					1, 1, school, group);
			student.setObjectId("asdgdsa");
			StaticDB.STUDENTS.add(student);
		}
	}
}
