package br.com.farofa.gm.test;

import br.com.farofa.gatopolisws.model.Room;
import br.com.farofa.gatopolisws.model.School;
import br.com.farofa.gatopolisws.model.SchoolData;
import br.com.farofa.gatopolisws.model.Teacher;

public class JsonBehaviourTest {
	public static void main(String[] args) {
		testModelClass();
	}
	
	public static void testModelClass () {
		SchoolData schoolData = new SchoolData ("12345678", "Escola");
		System.out.println(schoolData.getJson());
		
		School school = new School ("12345678", "123456789", "123456", "email@email.com", schoolData);
		System.out.println(school.getJson());
		
		Teacher teacher = new Teacher ("Professor", "Prof", "1234", "email@email.com", null, school);
		teacher.setId(1);
		System.out.println(teacher.getJson());
		
		Room room = new Room();
		System.out.println(room.getJson());
		
		/*schoolData = new SchoolData ("{\"inep\":\"12345678\",\"name\":\"Escola\"}");
		System.out.println(schoolData);
		school = new School ("{\"id\":\"12345678\",\"sync_code\":\"123456789\",\"email\":\"email@email.com\",\"schoolData\":\"12345678\",\"password\":\"123456\"}");
		System.out.println(school);
		teacher = new Teacher ("{\"id\":1,\"school\":\"12345678\",\"email\":\"email@email.com\",\"nickname\":\"Prof\",\"name\":\"Professor\",\"password\":\"1234\"}");
		System.out.println(teacher);
		room = new Room ("{\"id\":2,\"serie\":\"Serie\",\"term\":\"T\",\"name\":\"Room\",\"isDeleted\":false,\"teacher\":1}");
		System.out.println(room);*/
	}
	
	public static void testGenericSuperClass() {
		SchoolData sd = new SchoolData("123456789", "Escola");
		School school = new School("123456789", "123456478", "1234", "email@email.com", sd);
		System.out.println(school.getJson());
		
		School s2 = new School("{\"id\":\"123456789\",\"sync_code\":\"123456478\",\"email\":\"email@email.com\",\"schoolData\":\"123456789\",\"password\":\"1234\"}");
		System.out.println(s2);
	}
}