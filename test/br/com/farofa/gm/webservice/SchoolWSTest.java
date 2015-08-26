package br.com.farofa.gm.webservice;

import junit.framework.TestCase;

public class SchoolWSTest extends TestCase {

	/*@Test
	public void testSave() {
		SchoolDataWS sdWS = new SchoolDataWS();
		SchoolWS ws = new SchoolWS();
		
		int num = new Random().nextInt(99999999);
		String inep = String.valueOf(num);
		
		SchoolData sd = new SchoolData(inep, "Escola Teste");
		String json = sd.getJson();
		String id = sdWS.save(json);
		
		assertNotNull(json);
		assertNotNull(id);
		
		School school = new School(inep, "123456", "123456", "email@email.com", sd);
		json = school.getJson();
		id = ws.save(json);
		
		assertNotNull(json);
		assertNotNull(id);
		
		School expected = new School();
		json = ws.findById(inep);
		expected.setJson(json);
		
		assertNotNull(json);
		assertNotNull(expected.getId());
		
		assertEquals(expected.getId(), school.getId());
		assertEquals(expected.getEmail(), school.getEmail());
		assertEquals(expected.getPassword(), school.getPassword());
		assertEquals(expected.getSync_code(), school.getSync_code());
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByInep() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}
*/
}
