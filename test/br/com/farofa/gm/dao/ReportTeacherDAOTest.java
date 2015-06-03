package br.com.farofa.gm.dao;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;

import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.ReportTeacher;
import br.com.farofa.gm.model.Teacher;

public class ReportTeacherDAOTest extends TestCase {

	@Test
	public void testSave() {
		ReportTeacherDAO dao = new ReportTeacherDAOImpl(DataBaseManager.getEntityManager());
		Teacher teacher = new Teacher();
		teacher.setId(1);
		ReportTeacher rt = new ReportTeacher(null, 1, 1, 1, 1, 1, 1, 1, new Date(), teacher);
		dao.save(rt);
		
		assertNotNull(rt);
		assertNotNull(rt.getId());
		
		ReportTeacher expected = dao.findById(rt.getId());
		
		assertNotNull(expected);
		assertNotNull(expected.getId());
		
		assertEquals(expected.getAba_freq_duration(), rt.getAba_freq_duration());
		assertEquals(expected.getAba_obs_duration(), rt.getAba_obs_duration());
		assertEquals(expected.getAba_prod_duration(), rt.getAba_prod_duration());
		assertEquals(expected.getAccess(), rt.getAccess());
		assertEquals(expected.getDashboard_duration(), rt.getDashboard_duration());
		assertEquals(expected.getDashboard_opened(), rt.getDashboard_opened());
		assertEquals(expected.getGrouping_duration(), rt.getGrouping_duration());
		assertEquals(expected.getId(), rt.getId());
		assertEquals(expected.getTransitions_duration(), rt.getTransitions_duration());
		assertEquals(expected.getTeacher().getId(), rt.getTeacher().getId());
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
