package br.com.farofa.gm.webservice;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import br.com.farofa.gm.dao.BuildingsDAO;
import br.com.farofa.gm.dao.BuildingsDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.Buildings;

public class BuildingsWSTest {

	@Test
	public void testGenericWSImpl() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
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
		BuildingsDAO dao = new BuildingsDAOImpl(DataBaseManager.getEntityManager());
		List<Buildings> buildingsList = dao.findByInep("12345678");
		for (Buildings buildings : buildingsList) {
			System.out.println(buildings);
		}
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

}
