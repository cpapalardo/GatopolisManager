package br.com.motogatomanager.db;

import java.util.ArrayList;
import java.util.List;

import br.com.motogatomanager.modelo.Group;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.Student;
import br.com.motogatomanager.modelo.Teacher;

public class BancoLocal {
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

		if (BancoLocal.SCHOOLS == null) {
			BancoLocal.SCHOOLS = new ArrayList<School>();
			school = new School("1234", "Escola Teste", "1", "1");
			school.setObjectId("x4iv8ky");
			BancoLocal.SCHOOLS.add(school);
		}

		if (BancoLocal.TEACHERS == null) {
			BancoLocal.TEACHERS = new ArrayList<Teacher>();
			teacher1 = new Teacher("Professor", "Xavier", "1234",
					"email@email.com", true, "question", "answer", school,
					new byte[3]);
			teacher1.setObjectId("192j3u4");
			teacher2 = new Teacher("Professor", "Yield", "1234",
					"asdf@email.com", true, "question", "answer", school,
					new byte[3]);
			teacher2.setObjectId("12reyqr4");
			BancoLocal.TEACHERS.add(teacher1);
			BancoLocal.TEACHERS.add(teacher2);
		}

		if (BancoLocal.GROUPS == null) {
			BancoLocal.GROUPS = new ArrayList<Group>();
			group = new Group("Turma Marota", "1º ano A", "Manhã", null, school);
			group.setObjectId("1j902j3h");
			BancoLocal.GROUPS.add(group);
		}

		if (BancoLocal.STUDENTS == null) {
			BancoLocal.STUDENTS = new ArrayList<Student>();
			student = new Student("Aluno", "Sobrenome", "M", 1, "Pai", "xxx",
					1, 1, school, group);
			student.setObjectId("asdgdsa");
			BancoLocal.STUDENTS.add(student);
		}
	}
}
