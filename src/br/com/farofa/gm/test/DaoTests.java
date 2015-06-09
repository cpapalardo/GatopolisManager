package br.com.farofa.gm.test;

import java.util.Date;

import br.com.farofa.gm.dao.GameBirdDAO;
import br.com.farofa.gm.dao.GameBirdDAOImpl;
import br.com.farofa.gm.dao.TeacherDAO;
import br.com.farofa.gm.dao.TeacherDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.GameBird;
import br.com.farofa.gm.model.Student;
import br.com.farofa.gm.model.Teacher;
import br.com.farofa.gm.webservice.TeacherWS;

public class DaoTests {
	public static void main(String[] args) {
		//teacherTest();
	}
	
	public static void teacherTest() {
		TeacherWS ws = new TeacherWS();
		Teacher teacher = new Teacher();
		String json = ws.findById(1);
		System.out.println(json);
		teacher.setJson(json);
		
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
		GameBird gb = new GameBird(null, true, 1, "", "", new Date(), st);
		
		dao.save(gb);
		
		System.out.println(gb);
	}
}
