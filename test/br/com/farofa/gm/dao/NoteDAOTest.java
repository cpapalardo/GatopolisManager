package br.com.farofa.gm.dao;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;

import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.Note;
import br.com.farofa.gm.model.Student;
import br.com.farofa.gm.model.Teacher;

public class NoteDAOTest extends TestCase {

	@Test
	public void testSave() {
		NoteDAO dao = new NoteDAOImpl(DataBaseManager.getEntityManager());
		Teacher teacher = new Teacher();
		teacher.setId(1);
		Student student = new Student();
		student.setId(1);
		Note note = new Note(null, "Nota Teste", new Date(), teacher, student);
		dao.save(note);
		
		Note expected = dao.findById(note.getId());
		
		assertNotNull(expected);
		assertNotNull(expected.getId());
		
		assertEquals(expected.getNote(), note.getNote());
		assertEquals(expected.getCreated_at(), note.getCreated_at());
		assertEquals(expected.getId(), note.getId());
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
