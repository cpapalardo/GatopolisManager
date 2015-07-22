package br.com.farofa.gm.dao;

import junit.framework.TestCase;

public class TeacherDAOTest extends TestCase {

	/*@Test
	public void testSave() {
		TeacherDAO tDAO = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		
		SchoolData sd = new SchoolData();
		sd.setInep("12345678");
		School s = new School();
		s.setId("12345678");
		s.setSchoolData(sd);
		Teacher t = new Teacher(null, "Professor Teste", null, "1234", "email@email.com", 'A', "Resposta Teste", null, s);
		tDAO.save(t);
		
		Teacher expected = tDAO.findById(t.getId());
		
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
		TeacherDAO dao = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		Teacher teacher = dao.findById(1);
		
		teacher.setName("Professor Alterado");
		teacher.setEmail("emailalterado@email.com");
		teacher.setAnswer("Resposta Alterada");
		teacher.setNickname("Nick Name Alterado");
		teacher.setPassword("alterado");
		teacher.setQuestion('B');
		
		dao.update(teacher);
		
		Teacher expected = dao.findById(1);
		
		assertNotNull(expected);
		
		assertEquals(expected.getName(), teacher.getName());
		assertEquals(expected.getEmail(), teacher.getEmail());
		assertEquals(expected.getAnswer(), teacher.getAnswer());
		assertEquals(expected.getNickname(), teacher.getNickname());
		assertEquals(expected.getPassword(), teacher.getPassword());
		assertEquals(expected.getQuestion(), teacher.getQuestion());
	}

	@Test
	public void testDelete() {
		TeacherDAO tDAO = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		
		SchoolData sd = new SchoolData();
		sd.setInep("12345678");
		School s = new School();
		s.setId("12345678");
		s.setSchoolData(sd);
		Teacher t = new Teacher(null, "Professor Teste", null, "1234", "email@email.com", 'A', "Resposta Teste", null, s);
		tDAO.save(t);
		
		tDAO.delete(t);
		
		assertNotNull(t.getId());
		
		Teacher expected = tDAO.findById(t.getId());
		
		assertNull(expected);
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByInep() {
		TeacherDAO tDAO = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		Teacher teacher = tDAO.findById(1);
		assertNotNull(teacher);
	}

	@Test
	public void testFindAll() {
		TeacherDAO tDAO = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		
		List<Teacher> teachers = tDAO.findAll();
		
		assertNotNull(teachers);
		assertTrue(teachers.size() > 0);
	}*/

}
