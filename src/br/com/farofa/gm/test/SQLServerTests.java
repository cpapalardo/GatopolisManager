package br.com.farofa.gm.test;

import java.util.List;
import java.util.Random;

import br.com.farofa.gm.dao.RoomDAO;
import br.com.farofa.gm.dao.RoomDAOImpl;
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
import br.com.farofa.gm.model.Room;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.SchoolData;
import br.com.farofa.gm.model.Teacher;

public class SQLServerTests {
	static SchoolDataDAO schoolDataDAO;
	static SchoolDAO schoolDAO;
	static TeacherDAO teacherDAO;
	static RoomDAO groupDAO;
	static StudentDAO studentDAO;
	static ReportStudentDAO reportStudentDAO;
	static ReportTeacherDAO reportTeacherDAO;
	
	public static void main(String[] args) {
		//testSQLAzure();
	}
	
	public static void testSQLAzure(){
		SchoolDataDAO dao = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		SchoolData sd = new SchoolData("560650", "Escola X");
		dao.save(sd);
		DataBaseManager.close();
	}
	
	public static void getIdAfterSaveTest(){
		TeacherDAO dao = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		SchoolData sd = new SchoolData("12345678", "Escola X");
		School s = new School();
		s.setSchoolData(sd);
		Teacher teacher = new Teacher(null, "Nome", "haa", "1234", "email@email.com", 'A', "Resposta X", null, s);
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
		RoomDAO dao = new RoomDAOImpl(DataBaseManager.getEntityManager());
		Room group = dao.findByNameAndSerieAndPeriodAndInep("Class", "Serie", 'M', "12345678");
		System.out.println(group.getName());
		System.out.println(group.getTerm());
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
		RoomDAO dao = new RoomDAOImpl(DataBaseManager.getEntityManager());
		List<Room> groups = dao.findByInep("12345678");
		for (Room group : groups) {
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
