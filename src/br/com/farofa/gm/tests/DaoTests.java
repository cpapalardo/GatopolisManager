package br.com.farofa.gm.tests;

import br.com.farofa.gm.dao.SchoolDAO;
import br.com.farofa.gm.dao.SchoolDAOImpl;
import br.com.farofa.gm.dao.SchoolDataDAO;
import br.com.farofa.gm.dao.SchoolDataDAOImpl;
import br.com.farofa.gm.dao.TeacherDAO;
import br.com.farofa.gm.dao.TeacherDAOImpl;
import br.com.farofa.gm.database.DatabaseManager;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.SchoolData;
import br.com.farofa.gm.model.Teacher;

public class DaoTests {
	public static void main(String[] args) {
		testClose();
	}
	
	public static void testClose () {
		SchoolDataDAO schoolDataDAO = new SchoolDataDAOImpl ();
		schoolDataDAO.setEntityManager(DatabaseManager.getEntityManager());
		for (SchoolData sd : schoolDataDAO.findAll())
			System.out.println(sd);
		schoolDataDAO.close ();
		
		Teacher teacher = new Teacher ();
		teacher.setId(0);
		TeacherDAO teacherDAO = new TeacherDAOImpl ();
		teacherDAO.setEntityManager(DatabaseManager.getEntityManager());
		teacherDAO.save(teacher);
		teacherDAO.close();
		teacherDAO.setEntityManager(DatabaseManager.getEntityManager());
		for (Teacher t : teacherDAO.findAll())
			System.out.println(t);
		teacherDAO.close();
		
		/*for (SchoolData sd : schoolDataDAO.findAll())
			System.out.println(sd);
		schoolDataDAO.close ();*/
		
		SchoolDAO schoolDAO = new SchoolDAOImpl ();
		schoolDAO.setEntityManager(DatabaseManager.getEntityManager());
		for (School school : schoolDAO.findAll())
			System.out.println(school);
		schoolDAO.close();
	}
	
	public static void testSchoolData() {
		SchoolDataDAO dao = new SchoolDataDAOImpl ();
		dao.setEntityManager(DatabaseManager.getEntityManager());
		for (SchoolData sd : dao.findAll()) 
			System.out.println(sd);
	}
}
