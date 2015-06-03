package br.com.farofa.gm.webservice;

import java.util.Random;

import junit.framework.TestCase;

import org.junit.Test;

import br.com.farofa.gm.model.SchoolData;

public class SchoolDataWSTest extends TestCase {
	@Test
	public void testSave() {
		SchoolDataWS ws = new SchoolDataWS();
		
		int num = new Random().nextInt(99999999);
		String inep = String.valueOf(num);
		
		SchoolData actual = new SchoolData(inep, "Escola Teste");
		String json = actual.getJson();
		String id = ws.save(json);
		
		SchoolData expected = new SchoolData();
		json = ws.findById(inep);
		expected.setJson(json);
		
		assertEquals(id, inep);
		assertEquals(expected.getInep(), actual.getInep());
		assertEquals(expected.getName(), actual.getName());
	}

	@Test
	public void testUpdate() {
		
	}

	@Test
	public void testDelete() {
		//fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		//fail("Not yet implemented");
	}

	@Test
	public void testFindByInep() {
		//fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		//fail("Not yet implemented");
	}

}
