package br.com.farofa.gm.test;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.farofa.gm.dao.GenericDAO;
import br.com.farofa.gm.dao.GenericDAOImpl;
import br.com.farofa.gm.dao.SchoolDataDAO;
import br.com.farofa.gm.dao.SchoolDataDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.Group;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.SchoolData;
import br.com.farofa.gm.model.Student;
import br.com.farofa.gm.model.Teacher;
import br.com.farofa.gm.webservice.GroupWS;
import br.com.farofa.gm.webservice.SchoolDataWS;
import br.com.farofa.gm.webservice.SchoolWS;
import br.com.farofa.gm.webservice.StudentWS;
import br.com.farofa.gm.webservice.TeacherWS;

public class WebServiceTests {
	public static void main(String[] args) {
		testFindById();
	}
	
	public static void testFindById() {
		GenericDAO<SchoolData,String> dao = new GenericDAOImpl<SchoolData, String>(SchoolData.class, DataBaseManager.getEntityManager());
		SchoolData sd = dao.findById("12345678");
		System.out.println(sd);
	}
	
	public static void testGroupWeb() {
		GroupWS ws = new GroupWS();
		SchoolData sd = new SchoolData("12345678", "Escola Teste");
		School school = new School(sd, "1234567890", "1234", "escola@email.com");
		Teacher teacher = new Teacher(1, "Rodrigo de Sordi", "1234", "rodsordi@hotmail.com", null, null, null, school);
		Group group = new Group(null, "Grupo Malandro", "Seria Alpha", 'M', teacher, null);
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
		
		SchoolData sd = new SchoolData("12345678", "Escola Teste");
		School school = new School(sd, "1234567890", "1234", "escola@email.com");
		Teacher t2 = new Teacher(null, "Rodrigo de Sordi", "1234", "rodsordi@hotmail.com", null, null, null, school);
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
			
			actual = new School(sd, "1234567890", "12345678", "email@email.com");
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
