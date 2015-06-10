package br.com.farofa.gm.test;

import java.util.Date;
import java.util.Random;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.farofa.gm.dao.SchoolDAO;
import br.com.farofa.gm.dao.SchoolDAOImpl;
import br.com.farofa.gm.dao.SchoolDataDAO;
import br.com.farofa.gm.dao.SchoolDataDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.GameBird;
import br.com.farofa.gm.model.Room;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.SchoolData;
import br.com.farofa.gm.model.Student;
import br.com.farofa.gm.model.Teacher;
import br.com.farofa.gm.webservice.GameBirdWS;
import br.com.farofa.gm.webservice.RoomWS;
import br.com.farofa.gm.webservice.SchoolDataWS;
import br.com.farofa.gm.webservice.SchoolWS;
import br.com.farofa.gm.webservice.StudentWS;
import br.com.farofa.gm.webservice.TeacherWS;

public class WebServiceTests extends TestCase {
	public static void main(String[] args) {
		//testSaveSchool();
	}
	
	public static void testEntityId() {
		SchoolDataDAO sdDAO = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		SchoolDAO dao = new SchoolDAOImpl(DataBaseManager.getEntityManager());
		
		int num = new Random().nextInt(99999999);
		String inep = String.valueOf(num);
		
		SchoolData sd = new SchoolData(inep, "Escola Teste");
		sdDAO.save(sd);
		
		School school = new School(inep, "1234567890", "12345678", "email@email.com", sd);
		dao.save(school);
		
		School expected = dao.findById(inep);
		
		System.out.println(expected.getId());
		System.out.println(expected.getSchoolData().getName());
	}
	
	public static void testSaveSchool() {
		SchoolWS ws = new SchoolWS();
		String json = "{\"inep\":\"30679551\", \"sync_code\":\"1234567890\", \"password\":\"12345678\", \"email\":\"email@email.com\", \"schoolData\":{\"inep\":\"30679551\", \"name\":\"Escola Teste\"}}";
		ws.save(json);
		/*SchoolData sd = new SchoolData("12345678", "Escola Teste");
		School school = new School("12345678", "123456", "123456", "email@email.com", sd);
		json = school.getJson();
		System.out.println(json);*/
		//ws.save(json);
	}
	
	public static void testGameBird() {
		GameBirdWS ws = new GameBirdWS();
		Student student = new Student();
		student.setId(1);
		GameBird gb = new GameBird(null, true, 100, "", "", new Date(), student);
		String json = gb.getJson();
		
		String result = ws.save(json);
		
		//int id = Integer.valueOf(result);
		assertNotNull(result);
	}
	
	public static void testDelete() {
		SchoolDataWS ws = new SchoolDataWS();
		
		int num = new Random().nextInt(99999999);
		String inep = String.valueOf(num);
		
		SchoolData actual = new SchoolData(inep, "Escola Teste");
		String json = actual.getJson();
		ws.save(json);
		
		ws.delete(inep);
		
		json = ws.findById(inep);
	}
	
	public static void testFindById() {
		SchoolDataWS ws = new SchoolDataWS();
		
		String json = ws.findByInep("12345678");
		if (json == null) {
			SchoolData sd = new SchoolData("12345678", "Escola Teste");
			json = sd.getJson();
			ws.save(json);
			json = ws.findByInep("12345678");
		}
		
		JSONObject jsonObj = new JSONObject(json);
		
		SchoolData sd = new SchoolData();
		String s = jsonObj.get("SchoolData-0").toString();
		sd.setJson(s);
		
		//assertNotNull(json);
		//assertEquals("12345678", sd.getInep());
	}
	
	public static void testGroupWeb() {
		RoomWS ws = new RoomWS();
		SchoolData sd = new SchoolData("12345678", "Escola Teste");
		School school = new School("12345678", "1234567890", "1234", "escola@email.com", sd);
		Teacher teacher = new Teacher(1, "Rodrigo de Sordi", "1234", "rodsordi@hotmail.com", null, null, null, school);
		Room group = new Room(null, "Grupo Malandro", "Seria Alpha", 'M', teacher, null);
		String json = group.getJson();
		int id = Integer.valueOf(ws.save(json));
		group.setId(id);
		System.out.println(group);
	}
	
	public static void testStudentWeb() {
		StudentWS ws = new StudentWS();
		Student student = new Student();
		String json = ws.findById(2);
		student.setJson(json);
		System.out.println(student);
	}
	
	public static void testTeacherWeb(){
		TeacherWS ws = new TeacherWS();
		Teacher teacher = new Teacher();
		teacher.setJson(ws.findById(1));
		System.out.println(teacher);
		
		/*SchoolData sd = new SchoolData("12345678", "Escola Teste");
		School school = new School("12345678", "1234567890", "1234", "escola@email.com", sd);
		//Teacher t2 = new Teacher(null, "Rodrigo de Sordi", "1234", "rodsordi@hotmail.com", null, null, null, school);
		/*int id = ws.save(t2.getJson());
		t2.setId(id);
		System.out.println(t2);*/
		
	}
	
	public static void testSchool(){
		SchoolDataWS sdWS= new SchoolDataWS();
		SchoolWS schoolWS = new SchoolWS();
		
		//Actual
		School actual = null;
		String id = null;
		
		String json = sdWS.findAll();
		JSONObject jsonObj = null;
		if (json != null && !json.equals(""))
			jsonObj = new JSONObject(json);
		
		if (jsonObj.length() > 0) {
			SchoolData sd = new SchoolData();
			sd.setJson(jsonObj.getJSONObject("SchoolDataWeb-0").toString());
			
			System.out.println("sd.getName() = " + sd.getName());
			
			actual = new School(sd.getInep(), "1234567890", "12345678", "email@email.com", sd);
			schoolWS.update(actual.getJson());
			id = sd.getInep();
		}
		
		//Expected
		School expected = new School();
		json = schoolWS.findById(id);
		expected.setJson(json);
	}
	
	public static void testFindAll(){
		SchoolDataWS ws = new SchoolDataWS();
		String json = ws.findAll();
		System.out.println(json);
	}
	
	public static void findByIdTest(){
		SchoolDataWS ws = new SchoolDataWS();
		String json = ws.findById("12345678");
		System.out.println(json);
	}
	
	public static void deleteTest(){
		SchoolDataWS ws = new SchoolDataWS();
		SchoolData sd = new SchoolData("8970660", "Escola teste");
		ws.delete(sd.getJson());
	}
	
	public static void updateTest(){
		SchoolDataWS ws = new SchoolDataWS();
		SchoolData sd = new SchoolData("12345678", "Escola aklsjhdfkla");
		ws.update(sd.getJson());
	}
	
	public static void findByInepTest(){
		SchoolDataWS ws = new SchoolDataWS();
		String json = ws.findByInep("12345678");
		System.out.println(json);
	}
	
	public static void listTest(){
		String json1 = "{\"inep\":\"4560546\",\"name\":\"Escola teste1\"}";
		String json2 = "{\"inep\":\"5460450\",\"name\":\"Escola teste2\"}";
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("school1", json1);
		jsonObj.put("school2", json2);
		
		System.out.println(jsonObj.toString());
		
		JSONArray jsonArray = jsonObj.getJSONArray("school1");
		System.out.println(jsonArray.get(1));
	}
	
	public static void firstTest(){
		SchoolData sd = new SchoolData("4560546", "Escola teste");
		SchoolDataWS ws = new SchoolDataWS();
		ws.save(sd.getJson().toString());
	}
}
