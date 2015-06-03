package br.com.farofa.gm.dao;

import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import org.junit.Test;

import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.SchoolData;

public class SchoolDataDAOTest extends TestCase {

	@Test
	public void testSave() {
		SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		
		int num = new Random().nextInt(99999999);
		String inep = String.valueOf(num);
		
		SchoolData actual = new SchoolData(inep, "Escola Teste");
		dao.save(actual);
		
		SchoolData expected = dao.findById(inep);
		
		assertEquals(expected.getInep(), actual.getInep());
		assertEquals(expected.getName(), actual.getName());
	}

	@Test
	public void testUpdate() {
		SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		
		SchoolData actual = dao.findById("12345678");
		if (actual == null) {
			actual = new SchoolData("12345678", "Escola Teste");
			dao.save(actual);
		}
		if (actual.getName().equals("Escola Teste"))
			actual.setName("Escola Alterada");
		else
			actual.setName("Escola Teste");
		dao.update(actual);
		
		SchoolData expected = dao.findById("12345678");
		
		assertEquals(expected.getInep(), actual.getInep());
		assertEquals(expected.getName(), actual.getName());
	}

	@Test
	public void testDelete() {
		SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		
		int num = new Random().nextInt(99999999);
		String inep = String.valueOf(num);
		
		SchoolData actual = new SchoolData(inep, "Escola Teste");
		dao.save(actual);
		
		dao.delete(actual);
		
		SchoolData expected = dao.findById(inep);
		
		assertNull(expected);
	}

	@Test
	public void testFindById() {
		SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		
		SchoolData object = dao.findById("12345678");
		if (object == null) {
			object = new SchoolData("12345678", "Escola Teste");
			dao.save(object);
			object = dao.findById("12345678");
		}
		
		assertNotNull(object);
	}

	@Test
	public void testFindAll() {
		SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		
		List<SchoolData> sdList = dao.findAll();
		if (sdList == null || sdList.size() == 0) {
			for (int i = 0; i < 10; i++) {
				int num = new Random().nextInt(99999999);
				String inep = String.valueOf(num);
				SchoolData sd = new SchoolData(inep, "Escola Teste");
				dao.save(sd);
			}
			sdList = dao.findAll();
			assertEquals(10, sdList.size());
		}
		
		assertNotNull(sdList);
		assertTrue(sdList.size() > 0);
	}

	@Test
	public void testFindByInep() {
		SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		
		List<SchoolData> sdList = dao.findByInep("12345678");
		if (sdList == null || sdList.size() == 0) {
			SchoolData sd = new SchoolData("12345678", "Escola Teste");
			dao.save(sd);
			sdList = dao.findByInep("12345678");
		}
		
		assertNotNull(sdList);
		assertTrue(sdList.size() > 0);
		assertEquals("12345678", sdList.get(0).getInep());
	}

}
