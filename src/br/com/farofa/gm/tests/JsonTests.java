package br.com.farofa.gm.tests;

import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.Teacher;

public class JsonTests {
	public static void main(String[] args) {
		testVerJson ();
	}
	
	public static void testVerJson () {
		School school = new School ();
		school.setId("123456");
		Teacher teacher = new Teacher ("Rodrigo", "Rodrigo","qwer1234", "email@email.com", null, school);
		System.out.println(teacher.getJson());
	}
}
