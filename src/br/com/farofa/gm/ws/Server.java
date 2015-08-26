package br.com.farofa.gm.ws;

import java.util.List;

import br.com.farofa.gm.dao.BuildingDAO;
import br.com.farofa.gm.dao.BuildingDAOImpl;
import br.com.farofa.gm.dao.GameBirdDAO;
import br.com.farofa.gm.dao.GameBirdDAOImpl;
import br.com.farofa.gm.dao.GameSuperCatChallengeDAO;
import br.com.farofa.gm.dao.GameSuperCatChallengeDAOImpl;
import br.com.farofa.gm.dao.GameSuperCatDAO;
import br.com.farofa.gm.dao.GameSuperCatDAOImpl;
import br.com.farofa.gm.dao.NoteDAO;
import br.com.farofa.gm.dao.NoteDAOImpl;
import br.com.farofa.gm.dao.ReportStudentDAO;
import br.com.farofa.gm.dao.ReportStudentDAOImpl;
import br.com.farofa.gm.dao.ReportTeacherDAO;
import br.com.farofa.gm.dao.ReportTeacherDAOImpl;
import br.com.farofa.gm.dao.RoomDAO;
import br.com.farofa.gm.dao.RoomDAOImpl;
import br.com.farofa.gm.dao.SchoolDAO;
import br.com.farofa.gm.dao.SchoolDAOImpl;
import br.com.farofa.gm.dao.SchoolDataDAO;
import br.com.farofa.gm.dao.SchoolDataDAOImpl;
import br.com.farofa.gm.dao.StudentDAO;
import br.com.farofa.gm.dao.StudentDAOImpl;
import br.com.farofa.gm.dao.TeacherDAO;
import br.com.farofa.gm.dao.TeacherDAOImpl;
import br.com.farofa.gm.dao.TransitionDAO;
import br.com.farofa.gm.dao.TransitionDAOImpl;
import br.com.farofa.gm.database.DatabaseManager;
import br.com.farofa.gm.model.Building;
import br.com.farofa.gm.model.GameBird;
import br.com.farofa.gm.model.GameSuperCat;
import br.com.farofa.gm.model.GameSuperCatChallenge;
import br.com.farofa.gm.model.JsonBehaviour;
import br.com.farofa.gm.model.Note;
import br.com.farofa.gm.model.ReportStudent;
import br.com.farofa.gm.model.ReportTeacher;
import br.com.farofa.gm.model.Room;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.SchoolData;
import br.com.farofa.gm.model.Student;
import br.com.farofa.gm.model.Teacher;
import br.com.farofa.gm.model.Transition;
import br.com.farofa.gm.webservice.WebServiceExeptionManager;

public class Server {
	//School Data
	
	public String findSchoolDataByInep (String inep) {
		String result = null;
		try {
			SchoolDataDAO dao = new SchoolDataDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			SchoolData schoolData = dao.findById(inep);
			
			if (schoolData != null) 
				result = schoolData.getJson();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
	
	//School
	
	public String saveOrUpdateSchool (String json) {
		String result = null;
		
		try {
			School school = new School (json);
			SchoolDAO dao = new SchoolDAOImpl ();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			if ("" == school.getId()) 
				dao.save(school);
			else
				dao.update(school);
			result = school.getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		}
		return result;
	}
	
	public String findSchoolByInep (String inep) {
		String result = null;
		try {
			SchoolDAO dao = new SchoolDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			School school = dao.findById(inep);
			if (school != null) 
				result = school.getJson();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
	
	public String findSchoolBySyncCode (String syncCode) {
		String result = null;
		try {
			SchoolDAO dao = new SchoolDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			School school = dao.findBySyncCode(syncCode);
			if (school != null) 
				result = school.getJson();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
	
	public String findSchoolByName (String name) {
		String result = null;
		try {
			SchoolDAO dao = new SchoolDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			School school = dao.findByName(name);
			if (school != null)
				result = school.getJson();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
	
	//Teacher
	
	public String saveOrUpdateTeacher (String json) {
		String result = null;
		try {
			Teacher teacher = new Teacher (json);
			TeacherDAO dao = new TeacherDAOImpl ();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			if (teacher != null && (teacher.getId() == null || teacher.getId() == 0)) 
				dao.save(teacher);
			else
				dao.update(teacher);
			result = teacher.getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		}
		return result;
	}
	
	public String findTeacherByInep (String inep) {
		String result = null;
		try {
			TeacherDAO dao = new TeacherDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			List<Teacher> teachers = dao.findByInep(inep);
			result = JsonBehaviour.getJsonFromList(teachers);
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
	
	//Room
	
	public String saveOrUpdateRoom(String json) {
		String result = null;
		try {
			Room room = new Room(json);
			RoomDAO dao = new RoomDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			if (room != null && (room.getId() == null || room.getId() == 0))
				dao.save(room);
			else
				dao.update(room);
			result = room.getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		}
		return result;
	}

	public String findRoomByInep(String inep) {
		String result = null;
		try {
			RoomDAO dao = new RoomDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			List<Room> rooms = dao.findByInep(inep);
			result = JsonBehaviour.getJsonFromList(rooms);
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
	
	//Student
	
	public String saveOrUpdateStudent(String json) {
		String result = null;
		try {
			Student student = new Student(json);
			StudentDAO dao = new StudentDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			if (student != null && (student.getId() == null || student.getId() == 0))
				dao.save(student);
			else
				dao.update(student);
			result = student.getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		}
		return result;
	}

	public String findStudentByInep(String inep) {
		String result = null;
		try {
			StudentDAO dao = new StudentDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			List<Student> students = dao.findByInep(inep);
			result = JsonBehaviour.getJsonFromList(students);
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
	
	//Game Super Cat
	
	public String saveOrUpdateGameSuperCat(String json) {
		String result = null;
		try {
			GameSuperCat gameSuperCat = new GameSuperCat(json);
			GameSuperCatDAO dao = new GameSuperCatDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			if (gameSuperCat != null && (gameSuperCat.getId() == null || gameSuperCat.getId() == 0))
				dao.save(gameSuperCat);
			else
				dao.update(gameSuperCat);
			result = gameSuperCat.getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		}
		return result;
	}

	public String findGameSuperCatByInep(String inep) {
		String result = null;
		try {
			GameSuperCatDAO dao = new GameSuperCatDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			List<GameSuperCat> gameSuperCats = dao.findByInep(inep);
			result = JsonBehaviour.getJsonFromList(gameSuperCats);
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
	
	//Game Bird
	
	public String saveOrUpdateGameBird(String json) {
		String result = null;
		try {
			GameBird gameBird = new GameBird(json);
			GameBirdDAO dao = new GameBirdDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			if (gameBird != null && (gameBird.getId() == null || gameBird.getId() == 0))
				dao.save(gameBird);
			else
				dao.update(gameBird);
			result = gameBird.getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		}
		return result;
	}

	public String findGameBirdByInep(String inep) {
		String result = null;
		try {
			GameBirdDAO dao = new GameBirdDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			List<GameBird> gameBirds = dao.findByInep(inep);
			result = JsonBehaviour.getJsonFromList(gameBirds);
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
	
	//Building
	
	public String saveOrUpdateBuilding(String json) {
		String result = null;
		try {
			Building building = new Building(json);
			BuildingDAO dao = new BuildingDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			if (building != null && (building.getId() == null || building.getId() == 0))
				dao.save(building);
			else
				dao.update(building);
			result = building.getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		}
		return result;
	}

	public String findBuildingByInep(String inep) {
		String result = null;
		try {
			BuildingDAO dao = new BuildingDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			List<Building> buildings = dao.findByInep(inep);
			result = JsonBehaviour.getJsonFromList(buildings);
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
	//Note
	
	public String saveOrUpdateNote(String json) {
		String result = null;
		try {
			Note note = new Note(json);
			NoteDAO dao = new NoteDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			if (note != null && (note.getId() == null || note.getId() == 0))
				dao.save(note);
			else
				dao.update(note);
			result = note.getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		}
		return result;
	}

	public String findNoteByInep(String inep) {
		String result = null;
		try {
			NoteDAO dao = new NoteDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			List<Note> notes = dao.findByInep(inep);
			result = JsonBehaviour.getJsonFromList(notes);
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
	
	//Transition
	
	public String saveOrUpdateTransition(String json) {
		String result = null;
		try {
			Transition transition = new Transition(json);
			TransitionDAO dao = new TransitionDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			if (transition != null && (transition.getId() == null || transition.getId() == 0))
				dao.save(transition);
			else
				dao.update(transition);
			result = transition.getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		}
		return result;
	}

	public String findTransitionByInep(String inep) {
		String result = null;
		try {
			TransitionDAO dao = new TransitionDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			List<Transition> transitions = dao.findByInep(inep);
			result = JsonBehaviour.getJsonFromList(transitions);
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
	
	//Report Teacher
	
	public String saveOrUpdateReportTeacher(String json) {
		String result = null;
		try {
			ReportTeacher reportTeacher = new ReportTeacher(json);
			ReportTeacherDAO dao = new ReportTeacherDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			if (reportTeacher != null && (reportTeacher.getId() == null || reportTeacher.getId() == 0))
				dao.save(reportTeacher);
			else
				dao.update(reportTeacher);
			result = reportTeacher.getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		}
		return result;
	}

	public String findReportTeacherByInep(String inep) {
		String result = null;
		try {
			ReportTeacherDAO dao = new ReportTeacherDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			List<ReportTeacher> reportTeachers = dao.findByInep(inep);
			result = JsonBehaviour.getJsonFromList(reportTeachers);
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
	
	//Report Student
	
	public String saveOrUpdateReportStudent(String json) {
		String result = null;
		try {
			ReportStudent reportStudent = new ReportStudent(json);
			ReportStudentDAO dao = new ReportStudentDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			if (reportStudent != null && (reportStudent.getId() == null || reportStudent.getId() == 0))
				dao.save(reportStudent);
			else
				dao.update(reportStudent);
			result = reportStudent.getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		}
		return result;
	}

	public String findReportStudentByInep(String inep) {
		String result = null;
		try {
			ReportStudentDAO dao = new ReportStudentDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			List<ReportStudent> reportStudents = dao.findByInep(inep);
			result = JsonBehaviour.getJsonFromList(reportStudents);
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
	
	//Game Super Cat Challenge
	
	public String saveOrUpdateGameSuperCatChallenge(String json) {
		String result = null;
		try {
			GameSuperCatChallenge gameSuperCatChallenge = new GameSuperCatChallenge(json);
			GameSuperCatChallengeDAO dao = new GameSuperCatChallengeDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			if (gameSuperCatChallenge != null && (gameSuperCatChallenge.getId() == null || gameSuperCatChallenge.getId() == 0))
				dao.save(gameSuperCatChallenge);
			else
				dao.update(gameSuperCatChallenge);
			result = gameSuperCatChallenge.getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		}
		return result;
	}

	public String findGameSuperCatChallengeByInep(String inep) {
		String result = null;
		try {
			GameSuperCatChallengeDAO dao = new GameSuperCatChallengeDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			List<GameSuperCatChallenge> gameSuperCatChallenges = dao.findByInep(inep);
			result = JsonBehaviour.getJsonFromList(gameSuperCatChallenges);
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
}
