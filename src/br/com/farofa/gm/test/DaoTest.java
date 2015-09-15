package br.com.farofa.gm.test;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.farofa.gatopolisws.dao.BuildingDAO;
import br.com.farofa.gatopolisws.dao.BuildingDAOImpl;
import br.com.farofa.gatopolisws.dao.GameSuperCatChallengeDAO;
import br.com.farofa.gatopolisws.dao.GameSuperCatChallengeDAOImpl;
import br.com.farofa.gatopolisws.dao.GameSuperCatDAO;
import br.com.farofa.gatopolisws.dao.GameSuperCatDAOImpl;
import br.com.farofa.gatopolisws.dao.RoomDAO;
import br.com.farofa.gatopolisws.dao.RoomDAOImpl;
import br.com.farofa.gatopolisws.dao.SchoolDAO;
import br.com.farofa.gatopolisws.dao.SchoolDAOImpl;
import br.com.farofa.gatopolisws.dao.SchoolDataDAO;
import br.com.farofa.gatopolisws.dao.SchoolDataDAOImpl;
import br.com.farofa.gatopolisws.dao.StudentDAO;
import br.com.farofa.gatopolisws.dao.StudentDAOImpl;
import br.com.farofa.gatopolisws.dao.TeacherDAO;
import br.com.farofa.gatopolisws.dao.TeacherDAOImpl;
import br.com.farofa.gatopolisws.model.Building;
import br.com.farofa.gatopolisws.model.GameSuperCat;
import br.com.farofa.gatopolisws.model.GameSuperCatChallenge;
import br.com.farofa.gatopolisws.model.Room;
import br.com.farofa.gatopolisws.model.School;
import br.com.farofa.gatopolisws.model.SchoolData;
import br.com.farofa.gatopolisws.model.Student;
import br.com.farofa.gatopolisws.model.Teacher;
import br.com.farofa.gatopolisws.webservice.WebServiceExeptionManager;
import br.com.farofa.gm.database.DatabaseManager;

@Named("test")
@RequestScoped
public class DaoTest {
	public static void main(String[] args) {
		//testCreate();
		testException ();
	}
	
	public static void testException () {
		try {
			Teacher teacher = new Teacher ();
			TeacherDAOImpl teacherDAO = new TeacherDAOImpl ();
			teacherDAO.setEntityManager(DatabaseManager.getEntityManager());
			teacherDAO.save(teacher);
			System.out.println(teacher);
		} catch (Exception e) {
			System.out.println(WebServiceExeptionManager.getExceptionMessage(e));
			throw e;
		}
	}
	
	public static void testFindAll () {
		TeacherDAO teacherDAO = new TeacherDAOImpl ();
		teacherDAO.setEntityManager(DatabaseManager.getEntityManager());
		for (Teacher teacher : teacherDAO.findAll())
			System.out.println(teacher);
	}
	
	public static void testGSCC () {
		GameSuperCatChallengeDAO dao = new GameSuperCatChallengeDAOImpl();
		dao.setEntityManager(DatabaseManager.getEntityManager());
		List<GameSuperCatChallenge> gscc = dao.findByInep("12345678");
		for (GameSuperCatChallenge gameSuperCatChallenge : gscc) {
			System.out.println(gameSuperCatChallenge);
		}
	}
	
	public static void testGameSuperCat () {
		Student s = new Student ();
		s.setId(1);
		
		GameSuperCat word = new GameSuperCat ("Palavra", "Palabra", "NOT_ENOUGH_INPUT", 80, new Date(), s);
		GameSuperCatDAO wordDAO = new GameSuperCatDAOImpl();
		wordDAO.setEntityManager(DatabaseManager.getEntityManager());
		wordDAO.save(word);
		System.out.println("Word: " + word);
	}
	
	private static void testCreate () {
		SchoolDataDAO sdDAO = new SchoolDataDAOImpl();
		sdDAO.setEntityManager(DatabaseManager.getEntityManager());
		SchoolData schoolData = new SchoolData ("12345678", "Escola Teste");
		sdDAO.save(schoolData);
		System.out.println(schoolData);
		
		School school = new School ("12345678", "123456789", "123456", "email@email.com", schoolData);
		SchoolDAO schoolDAO = new SchoolDAOImpl ();
		schoolDAO.setEntityManager(DatabaseManager.getEntityManager());
		schoolDAO.save(school);
		
		TeacherDAO tDAO = new TeacherDAOImpl ();
		tDAO.setEntityManager(DatabaseManager.getEntityManager());
		Teacher teacher = new Teacher ("Teacher", "Teacher", "1234", "email@email.com", null, school);
		tDAO.save(teacher);
		System.out.println("Teacher: " + teacher);
		
		Room room = new Room ("Classe A", "Serie A", 'M', teacher, 1);
		RoomDAO rDAO = new RoomDAOImpl();
		rDAO.setEntityManager(DatabaseManager.getEntityManager());
		rDAO.save(room);
		System.out.println("Room: " + room);
		
		Student s = new Student ("Rodrigo", 'M', new Date(), "NOT_ENOUGH_INPUT", 1,1,1,1,null,room);
		StudentDAO sDAO = new StudentDAOImpl();
		sDAO.setEntityManager(DatabaseManager.getEntityManager());
		sDAO.save(s);
		System.out.println("Student: " + s);
		
		GameSuperCat word = new GameSuperCat ("Palavra", "Palabra", "NOT_ENOUGH_INPUT", 80, new Date(), s);
		GameSuperCatDAO wordDAO = new GameSuperCatDAOImpl();
		wordDAO.setEntityManager(DatabaseManager.getEntityManager());
		wordDAO.save(word);
		System.out.println("Word: " + word);
		
		System.out.println("completed");
	}
	
	@Inject
	private static SchoolDataDAO dao1;
	
	public static void testCDI () {
		System.out.println(dao1);
	}
	
	public static void testBuilding() {
		BuildingDAO dao = new BuildingDAOImpl();
		dao.setEntityManager(DatabaseManager.getEntityManager());
		for (Building b : dao.findAll()) {
			System.out.println(b);
			System.out.println(b.getJson());
		}
		
	}
	
	public static void testFindByInep() {
		SchoolDataDAO dao = new SchoolDataDAOImpl();
		EntityManager manager = Persistence.createEntityManagerFactory("banco_teste").createEntityManager();
		dao.setEntityManager(manager);
		List<SchoolData> sds = dao.findByInep("12345678");
		for (SchoolData schoolData : sds) {
			System.out.println(schoolData);
		}
	}
}
