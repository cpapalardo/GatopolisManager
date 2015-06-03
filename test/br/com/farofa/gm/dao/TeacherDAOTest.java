package br.com.farofa.gm.dao;

import junit.framework.TestCase;

import org.junit.Test;

import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.SchoolData;
import br.com.farofa.gm.model.Teacher;

public class TeacherDAOTest extends TestCase {

	@Test
	public void testSave() {
		TeacherDAO dao = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		
		SchoolData sd = new SchoolData();
		sd.setInep("12345678");
		School s = new School();
		s.setSchoolData(sd);
		Teacher t = new Teacher(null, "Professor Teste", "1234", "email@email.com", 'A', "Resposta Teste", null, s);
		dao.save(t);
		
		Teacher expected = dao.findById(t.getId());
		
		assertNotNull(expected);
		assertNotNull(t.getId());
		assertEquals(expected.getId(), t.getId());
		assertEquals(expected.getAnswer(), t.getAnswer());
		assertEquals(expected.getEmail(), t.getEmail());
		assertEquals(expected.getName(), t.getName());
		assertEquals(expected.getPassword(), t.getPassword());
		assertEquals(expected.getPicture_url(), t.getPicture_url());
		assertEquals(expected.getQuestion(), t.getQuestion());
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
