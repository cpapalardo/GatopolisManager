package br.com.farofa.gm.dao;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;

import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.GameBird;
import br.com.farofa.gm.model.Student;

public class GameBirdDA0Test extends TestCase{

	@Test
	public void testSave() {
		GameBirdDAO dao = new GameBirdDAOImpl(DataBaseManager.getEntityManager());
		
		Student st = new Student();
		st.setId(1);
		GameBird gb = new GameBird(null, true, 1, "", "", "", new Date(), st);
		dao.save(gb);
		
		GameBird expected = dao.findById(st.getId());
		
		assertNotNull(expected.getId());
		assertEquals(expected.getId(), st.getId());
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
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByInep() {
		fail("Not yet implemented");
	}

}
