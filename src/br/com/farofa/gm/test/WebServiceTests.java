package br.com.farofa.gm.test;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.farofa.gm.model.SchoolData;
import br.com.farofa.gm.webservice.SchoolDataWeb;

public class WebServiceTests {
	public static void main(String[] args) {
		findAllTest();
	}
	
	public static void findAllTest(){
		SchoolDataWeb ws = new SchoolDataWeb();
		String json = ws.findAll();
		System.out.println(json);
	}
	
	public static void findByIdTest(){
		SchoolDataWeb ws = new SchoolDataWeb();
		String json = ws.findById("12345678");
		System.out.println(json);
	}
	
	public static void deleteTest(){
		SchoolDataWeb ws = new SchoolDataWeb();
		SchoolData sd = new SchoolData("8970660", "Escola teste");
		ws.delete(sd.getJson());
	}
	
	public static void updateTest(){
		SchoolDataWeb ws = new SchoolDataWeb();
		SchoolData sd = new SchoolData("12345678", "Escola aklsjhdfkla");
		ws.update(sd.getJson());
	}
	
	public static void findByInepTest(){
		SchoolDataWeb ws = new SchoolDataWeb();
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
		SchoolDataWeb ws = new SchoolDataWeb();
		ws.save(sd.getJson().toString());
	}
}
