package br.com.farofa.gm.test;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import br.com.farofa.gm.dao.GroupDAO;
import br.com.farofa.gm.dao.GroupDAOImpl;
import br.com.farofa.gm.dao.SchoolDAO;
import br.com.farofa.gm.dao.SchoolDAOImpl;
import br.com.farofa.gm.dao.SchoolDataDAO;
import br.com.farofa.gm.dao.SchoolDataDAOImpl;
import br.com.farofa.gm.dao.TeacherDAO;
import br.com.farofa.gm.dao.TeacherDAOImpl;
import br.com.farofa.gm.model.Group;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.SchoolData;
import br.com.farofa.gm.model.Teacher;

public class SQLServerTests {
	public static void main(String[] args) {
		testConnection();
		testGroup();
	}
	
	public static void testConnection(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("banco_teste2");
	}
	
	public static void testGroup(){
		GroupDAO dao = new GroupDAOImpl();
		Group group = dao.findByNameAndSerieAndPeriodAndInep("Class", "Serie", 'M', 12345678);
		System.out.println(group.getName());
		System.out.println(group.getPeriod());
	}
	
	public static void testTeacher(){
		TeacherDAO dao = new TeacherDAOImpl();
		Teacher teacher = dao.findByNameAndInep("Professor", 12345678);
		System.out.println(teacher);
	}
	
	public static void testSchool(){
		SchoolDAO dao = new SchoolDAOImpl();
		School school = dao.findByName("a");
		System.out.println(school);
	}
	
	public static void testCount () {
		GroupDAO dao = new GroupDAOImpl();
		List<Group> groups = dao.findByInep(12345678);
		for (Group group : groups) {
			System.out.println(group.getQtdeAlunos());
		}
	}
	
	public static void testFindBySyncCode() {
		SchoolDAO dao = new SchoolDAOImpl();
		
		for (School a : dao.findByInep(12345678)) {
			System.out.println(a.getSchoolData().getName());
		}
	}
	
	public static void testCriteria() {
		SchoolDAOImpl dao = new SchoolDAOImpl();
		for (School school : dao.findByInep(12345678)) {
			System.out.println(school.getSync_code());
			System.out.println(school.getEmail());
			System.out.println(school.getSchoolData());
			System.out.println(school.getSchoolData().getName());
		}
		
		TeacherDAOImpl dao2 = new TeacherDAOImpl();
		for (Teacher teacher : dao2.findByInep(12345678)) {
			System.out.println(teacher.getName());
			System.out.println(teacher.getSchool().getSchoolData().getName());
		}
	}
	
	public static void testFindAll() {
		EntityManagerFactory factory = null;
		try {
			SchoolDataDAO dao = new SchoolDataDAOImpl ();
			List<SchoolData> list = dao.findAll();
			for (SchoolData schoolData : list) {
				System.out.println(schoolData.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}
	
	public static void testFind() {
		EntityManagerFactory factory = null;
		
		try {
			SchoolDataDAO dao = new SchoolDataDAOImpl ();
			Integer i = -1140087049;
			SchoolData sd = dao.findById(i);
			System.out.println(sd.getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}
	
	public static void testUpdate() {
		EntityManagerFactory factory = null;
		
		try {
			SchoolData sd = new SchoolData (-1140087049, "Escola");
			sd.setName("Escola Y");
			
			SchoolDataDAO dao = new SchoolDataDAOImpl ();
			dao.update(sd);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}
	
	public static void testSave() {
		EntityManagerFactory factory = null;
		
		try {
			int i = new Random().nextInt();
			System.out.println(i);
			SchoolData sd = new SchoolData (i, "Escola");
			
			SchoolDataDAO dao = new SchoolDataDAOImpl ();
			dao.save(sd);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}
	
}
