package br.com.farofa.gm.webservice;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;

import br.com.farofa.gm.model.GameBird;
import br.com.farofa.gm.model.Student;

public class GameBirdWSTest extends TestCase {

	@Test
	public void testSave() {
		GameBirdWS ws = new GameBirdWS();
		Student student = new Student();
		student.setId(1);
		GameBird gb = new GameBird(true, 100, "", "", "", new Date(), student);
		String json = gb.getJson();
		
		String result = ws.save(json);
		assertNotNull(result);
		
		int id = Integer.valueOf(result);
		
		String expected = ws.findById(id);
		
		assertNotNull(expected);
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

}
