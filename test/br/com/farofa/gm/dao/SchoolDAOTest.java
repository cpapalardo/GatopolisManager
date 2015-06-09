package br.com.farofa.gm.dao;

import java.util.Random;

import junit.framework.TestCase;

import org.junit.Test;

import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.SchoolData;

public class SchoolDAOTest extends TestCase {

	@Test
	public void testSave() {
		SchoolDataDAO sdDAO = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		SchoolDAO dao = new SchoolDAOImpl(DataBaseManager.getEntityManager());
		
		int num = new Random().nextInt(99999999);
		String inep = String.valueOf(num);
		
		SchoolData sd = new SchoolData(inep, "Escola Teste");
		sdDAO.save(sd);
		
		School school = new School(inep, "1234567890", "12345678", "email@email.com", sd);
		dao.save(school);
		
		School expected = dao.findById(inep);
		
		assertNotNull(expected);
		assertEquals(expected.getSchoolData().getInep(), inep);
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

}
