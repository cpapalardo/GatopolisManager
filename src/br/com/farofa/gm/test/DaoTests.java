package br.com.farofa.gm.test;

import java.util.Date;
import java.util.List;

import br.com.farofa.gm.dao.GameBirdDAO;
import br.com.farofa.gm.dao.GameBirdDAOImpl;
import br.com.farofa.gm.dao.TeacherDAO;
import br.com.farofa.gm.dao.TeacherDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.GameBird;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.SchoolData;
import br.com.farofa.gm.model.Student;
import br.com.farofa.gm.model.Teacher;

public class DaoTests {
	public static void main(String[] args) {
		teacherTest();
	}
	
	public static void testTeacherFindByInep () {
		TeacherDAO dao = new TeacherDAOImpl (DataBaseManager.getEntityManager());
		List<Teacher> teacher = dao.findByInep("12345678");
	}
	
	public static void teacherTest() {
		TeacherDAO dao = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		SchoolData sd = new SchoolData("12345678", "Escola Teste");
		School school = new School("12345678", "1234567890", "12345678", "email@email.com", sd);
		Teacher teacher = new Teacher(null,"Murilo", "Mumu", "1234", "email@email.com", 'A', "saklsdfj", null, school);
		
		dao.save(teacher);
	}
	
	public static void fetchTest () {
		TeacherDAO dao = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		Teacher teacher = dao.findById(1);
		System.out.println(teacher);
		System.out.println(teacher.getSchool());
		System.out.println(teacher.getSchool().getSync_code());
		System.out.println(teacher.getSchool().getSchoolData());
		System.out.println(teacher.getSchool().getSchoolData().getInep());
	}
	
	public static void gameBirdTest() {
		GameBirdDAO dao = new GameBirdDAOImpl(DataBaseManager.getEntityManager());
		
		Student st = new Student();
		st.setId(1);
		GameBird gb = new GameBird(null, true, 1, "", "", "", new Date(), st);
		
		dao.save(gb);
		
		System.out.println(gb);
	}
}
