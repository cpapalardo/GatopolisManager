package br.com.farofa.gm.webservice;

import junit.framework.TestCase;

public class SchoolDataWSTest extends TestCase {
	/*@Test
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
		SchoolDataWS ws = new SchoolDataWS();
		
		String json = ws.findById("12345678");
		SchoolData actual = new SchoolData();
		actual.setJson(json);
		if (json == null || json.equals("")) {
			actual = new SchoolData("12345678", "Escola Teste");
			json = actual.getJson();
			ws.save(json);
		}
		if (actual.getName().equals("Escola Teste"))
			actual.setName("Escola Alterada");
		else
			actual.setName("Escola Teste");
		json = actual.getJson();
		ws.update(json);
		
		SchoolData expected = new SchoolData();
		json = ws.findById("12345678");
		expected.setJson(json);
		
		assertEquals(expected.getInep(), actual.getInep());
		assertEquals(expected.getName(), actual.getName());
	}

	@Test
	public void testDelete() {
		SchoolDataWS ws = new SchoolDataWS();
		
		int num = new Random().nextInt(99999999);
		String inep = String.valueOf(num);
		SchoolData actual = new SchoolData(inep, "Escola Teste");
		String json = actual.getJson();
		ws.save(json);
		ws.delete(json);
		
		json = ws.findById(inep);
		
		assertNull(json);
	}

	@Test
	public void testFindById() {
		SchoolDataWS ws = new SchoolDataWS();
		
		String json = ws.findById("12345678");
		if (json == null) {
			SchoolData sd = new SchoolData("12345678", "Escola Teste");
			json = sd.getJson();
			ws.save(json);
			json = ws.findById("12345678");
		}
		
		assertNotNull(json);
	}

	@Test
	public void testFindByInep() {
		SchoolDataWS ws = new SchoolDataWS();
		
		String json = ws.findByInep("12345678");
		if (json == null) {
			SchoolData sd = new SchoolData("12345678", "Escola Teste");
			json = sd.getJson();
			ws.save(json);
			json = ws.findByInep("12345678");
		}
		
		assertTrue(json.contains("SchoolData-0"));
		
		JSONObject jsonObj = new JSONObject(json);
		
		SchoolData sd = new SchoolData();
		String s = jsonObj.get("SchoolData-0").toString();
		sd.setJson(s);
		
		assertNotNull(json);
		assertNotNull(s);
		assertEquals("12345678", sd.getInep());
	}

	@Test
	public void testFindAll() {
		SchoolDataWS ws = new SchoolDataWS();
		
		String json = ws.findAll();
		if (json == null) {
			for (int i = 0; i < 10; i++) {
				int num = new Random().nextInt(99999999);
				String inep = String.valueOf(num);
				SchoolData sd = new SchoolData(inep, "Escola Teste - " + i);
				json = sd.getJson();
				ws.save(json);
			}
			json = ws.findAll();
			assertNotNull(json);
			assertTrue(json.contains("SchoolData-0"));
			assertTrue(json.contains("SchoolData-1"));
			assertTrue(json.contains("SchoolData-2"));
			assertTrue(json.contains("SchoolData-3"));
			assertTrue(json.contains("SchoolData-4"));
			assertTrue(json.contains("SchoolData-5"));
			assertTrue(json.contains("SchoolData-6"));
			assertTrue(json.contains("SchoolData-7"));
			assertTrue(json.contains("SchoolData-8"));
			assertTrue(json.contains("SchoolData-9"));
		}
		
		JSONObject jsonObj = new JSONObject(json);
		SchoolData sd = new SchoolData();
		String s = jsonObj.get("SchoolData-0").toString();
		sd.setJson(s);
		
		assertNotNull(json);
		assertNotNull(s);
		assertTrue(json.contains("SchoolData-0"));
	}*/

}
