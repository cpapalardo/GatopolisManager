package br.com.farofa.gm.test;

import junit.framework.TestCase;
import br.com.farofa.gm.model.SchoolData;
import br.com.farofa.gm.webservice.SchoolDataWeb;

public class SchoolDataWebTest extends TestCase {
	SchoolDataWeb ws = new SchoolDataWeb();
	
	public void webServiceTest(){
		SchoolData actual = new SchoolData("12345678", "Escola Teste");
		ws.update(actual.getJson());
		
		String json = ws.findById("12345678");
		SchoolData expected = new SchoolData();
		expected.setJson(json);
		
		System.out.println(expected.getInep());
		System.out.println(actual.getInep());
		System.out.println(expected.getName());
		System.out.println(actual.getName());
		
		assertEquals(expected.getInep(), actual.getInep());
		assertEquals(expected.getName(), actual.getName());
	}
}
