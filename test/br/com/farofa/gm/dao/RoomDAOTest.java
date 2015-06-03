package br.com.farofa.gm.dao;

import junit.framework.TestCase;

import org.junit.Test;

import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.Room;
import br.com.farofa.gm.model.Teacher;

public class RoomDAOTest extends TestCase {

	@Test
	public void testSave() {
		RoomDAO dao = new RoomDAOImpl(DataBaseManager.getEntityManager());
		Teacher teacher = new Teacher();
		teacher.setId(1);
		Room room = new Room(null, "Sala Teste", "Serie Teste", 'M', teacher, null);
		dao.save(room);
		
		Room expected = dao.findById(room.getId());
		
		assertNotNull(expected);
		assertNotNull(expected.getId());
		assertEquals(expected.getId(), room.getId());
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
