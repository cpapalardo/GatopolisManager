package br.com.farofa.gm.test;

import java.util.List;
import java.util.Random;

import br.com.farofa.gm.dao.GroupDAO;
import br.com.farofa.gm.dao.GroupDAOImpl;
import br.com.farofa.gm.dao.ReportStudentDAO;
import br.com.farofa.gm.dao.ReportTeacherDAO;
import br.com.farofa.gm.dao.SchoolDAO;
import br.com.farofa.gm.dao.SchoolDAOImpl;
import br.com.farofa.gm.dao.SchoolDataDAO;
import br.com.farofa.gm.dao.SchoolDataDAOImpl;
import br.com.farofa.gm.dao.StudentDAO;
import br.com.farofa.gm.dao.TeacherDAO;
import br.com.farofa.gm.dao.TeacherDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.Group;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.SchoolData;
import br.com.farofa.gm.model.Teacher;
import br.com.farofa.gm.model.Transition;
import br.com.farofa.gm.model.WrittenWord;

public class SQLServerTests {
	static SchoolDataDAO schoolDataDAO;
	static SchoolDAO schoolDAO;
	static TeacherDAO teacherDAO;
	static GroupDAO groupDAO;
	static StudentDAO studentDAO;
	static Transition transitionDAO;
	static WrittenWord writtenWordDAO;
	static ReportStudentDAO reportStudentDAO;
	static ReportTeacherDAO reportTeacherDAO;
	
	public static void main(String[] args) {
		getIdAfterSaveTest();
	}
	
	public static void getIdAfterSaveTest(){
		TeacherDAO dao = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		SchoolData sd = new SchoolData("12345678", "Escola X");
		School s = new School();
		s.setSchoolData(sd);
		Teacher teacher = new Teacher(null, "Nome", "1234", "email@email.com", 'A', "Resposta X", null, s);
		dao.save(teacher);
		System.out.println(teacher.getId());
		DataBaseManager.close();
	}
	
	public static void testConnection(){
		SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		SchoolData sd = new SchoolData("1234", "Escola Teste");
		sd.setName("Escola W");
		dao.update(sd);
		
		sd.setName("Escola Z");
		dao.update(sd);
		
		System.out.println(dao.findByInep("12345678").get(0).getName());
		
		TeacherDAO tDAO = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		System.out.println(tDAO.findByInep("12345678").get(0).getName());
		
		DataBaseManager.close();
	}
	
	public static void testGroup(){
		GroupDAO dao = new GroupDAOImpl(DataBaseManager.getEntityManager());
		Group group = dao.findByNameAndSerieAndPeriodAndInep("Class", "Serie", 'M', "12345678");
		System.out.println(group.getName());
		System.out.println(group.getPeriod());
		DataBaseManager.close();
	}
	
	public static void testTeacher(){
		TeacherDAO dao = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		Teacher teacher = dao.findByNameAndInep("Professor", "12345678");
		System.out.println(teacher);
		DataBaseManager.close();
	}
	
	public static void testSchool(){
		SchoolDAO dao = new SchoolDAOImpl(DataBaseManager.getEntityManager());
		School school = dao.findByName("a");
		System.out.println(school);
		DataBaseManager.close();
	}
	
	public static void testCount () {
		GroupDAO dao = new GroupDAOImpl(DataBaseManager.getEntityManager());
		List<Group> groups = dao.findByInep("12345678");
		for (Group group : groups) {
			System.out.println(group.getQtdeAlunos());
		}
		DataBaseManager.close();
	}
	
	public static void testFindBySyncCode() {
		SchoolDAO dao = new SchoolDAOImpl(DataBaseManager.getEntityManager());
		
		for (School a : dao.findByInep("12345678")) {
			System.out.println(a.getSchoolData().getName());
		}
		DataBaseManager.close();
	}
	
	public static void testCriteria() {
		SchoolDAOImpl dao = new SchoolDAOImpl(DataBaseManager.getEntityManager());
		for (School school : dao.findByInep("12345678")) {
			System.out.println(school.getSync_code());
			System.out.println(school.getEmail());
			System.out.println(school.getSchoolData());
			System.out.println(school.getSchoolData().getName());
		}
		
		TeacherDAOImpl dao2 = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		for (Teacher teacher : dao2.findByInep("12345678")) {
			System.out.println(teacher.getName());
			System.out.println(teacher.getSchool().getSchoolData().getName());
		}
		DataBaseManager.close();
	}
	
	public static void testFindAll() {
		try {
			SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
			List<SchoolData> list = dao.findAll();
			for (SchoolData schoolData : list) {
				System.out.println(schoolData.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
	}
	
	public static void testFind() {
		try {
			SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
			Integer i = -1140087049;
			SchoolData sd = dao.findById(String.valueOf(i));
			System.out.println(sd.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
	}
	
	public static void testUpdate() {
		try {
			SchoolData sd = new SchoolData ("-1140087049", "Escola");
			sd.setName("Escola Y");
			
			SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
			dao.update(sd);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
	}
	
	public static void testSave() {
		try {
			int i = new Random().nextInt();
			System.out.println(i);
			SchoolData sd = new SchoolData (String.valueOf(i), "Escola");
			
			SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
			dao.save(sd);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataBaseManager.close();
	}
	
}
