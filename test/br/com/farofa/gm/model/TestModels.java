package br.com.farofa.gm.model;

import java.util.Date;
import java.util.List;
import java.util.Random;

import br.com.farofa.gm.dao.GroupDAO;
import br.com.farofa.gm.dao.GroupDAOImpl;
import br.com.farofa.gm.dao.NoteDAO;
import br.com.farofa.gm.dao.NoteDAOImpl;
import br.com.farofa.gm.dao.ReportStudentDAO;
import br.com.farofa.gm.dao.ReportStudentDAOImpl;
import br.com.farofa.gm.dao.ReportTeacherDAO;
import br.com.farofa.gm.dao.ReportTeacherDAOImpl;
import br.com.farofa.gm.dao.SchoolDAO;
import br.com.farofa.gm.dao.SchoolDAOImpl;
import br.com.farofa.gm.dao.SchoolDataDAO;
import br.com.farofa.gm.dao.SchoolDataDAOImpl;
import br.com.farofa.gm.dao.StudentDAO;
import br.com.farofa.gm.dao.StudentDAOImpl;
import br.com.farofa.gm.dao.TeacherDAO;
import br.com.farofa.gm.dao.TeacherDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;

public class TestModels {
	private String inep;
	
	private SchoolData schoolData;
	private School school;
	private Teacher teacher;
	private Group group;
	private Student student;
	private Transition transition;
	private WrittenWord writtenWord;
	private Note note;
	private ReportTeacher rt;
	private ReportStudent rs;
	
	public TestModels() {
		int num = new Random().nextInt(99999999);
		this.inep = String.valueOf(num);
		testInitiate();
	}
	
	public void testInitiate() {
		schoolData = new SchoolData(inep, "Escola Teste");
		school = new School(schoolData, "1234567890", "12345678", "school@email.com");
		teacher = new Teacher(null, "Professor Teste","1234", "professor@email.com", 'A', "Resposta Teste", null, school);
		group = new Group(null, "Turma Teste", "Serie Teste", 'M', teacher, null);
		student = new Student(null, "Aluno Teste", 'M', new Date(), "NOT_ENOUGH_INPUT", 1, 1, 1, null, group);
		transition = new Transition(null, "NOT_ENOUGH_INPUT", new Date(), student);
		writtenWord = new WrittenWord(null, "palavra teste", "palavra errada", "akljsdhflkahsdf", "NOT_ENOUGH_INPUT", new Date(), student);
		note = new Note(null, "Nota Teste", new Date(), teacher, student);
		rt = new ReportTeacher(null, 1, 1, 1, 1, 1, 1, 1, teacher);
		rs = new ReportStudent(null, new Date(), 1, teacher, student);
	}
	
	public void testExistent() {
		SchoolDataDAO schoolDataDAO = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
		SchoolDAO schoolDAO = new SchoolDAOImpl(DataBaseManager.getEntityManager());
		TeacherDAO teacherDAO = new TeacherDAOImpl(DataBaseManager.getEntityManager());
		GroupDAO groupDAO = new GroupDAOImpl(DataBaseManager.getEntityManager());
		StudentDAO studentDAO = new StudentDAOImpl(DataBaseManager.getEntityManager());
		NoteDAO noteDAO = new NoteDAOImpl(DataBaseManager.getEntityManager());
		ReportTeacherDAO reportTeacherDAO = new ReportTeacherDAOImpl(DataBaseManager.getEntityManager());
		ReportStudentDAO reportStudentDAO = new ReportStudentDAOImpl(DataBaseManager.getEntityManager());
		
		List<SchoolData> schoolDataList = schoolDataDAO.findByInep(inep);
		List<School> schoolList = schoolDAO.findByInep(inep);
		List<Teacher> teacherList = teacherDAO.findByInep(inep);
		List<Group> groupList = groupDAO.findByInep(inep);
		List<Student> studentList = studentDAO.findByInep(inep);
		List<Note> noteList = noteDAO.findByInep(inep);
		List<ReportTeacher> rtList = reportTeacherDAO.findByInep(inep);
		List<ReportStudent> rsList = reportStudentDAO.findByInep(inep);
		
		if (schoolDataList.size() > 0) schoolData = schoolDataList.get(0);
		if (schoolList.size() > 0) school = schoolList.get(0);
		if (teacherList.size() > 0) teacher = teacherList.get(0);
		if (groupList.size() > 0) group = groupList.get(0);
		if (studentList.size() > 0) student = studentList.get(0);
		if (noteList.size() > 0) note = noteList.get(0);
		if (rtList.size() > 0) rt = rtList.get(0);
		if (rsList.size() > 0) rs = rsList.get(0);
	}
	
	public void testDiferent() {
		testExistent();
		
		schoolData = new SchoolData("12345678", "Escola Alterada");
		school = new School(schoolData, "1234567890", "12345678", "alterado@email.com");
		teacher = new Teacher(1, "Professor Alterado","1234", "alterado@email.com", 'A', "Resposta Alterado", null, school);
		group = new Group(1, "Turma Alterada", "Serie Alterada", 'T', teacher, null);
		student = new Student(1, "Aluno Alterado", 'F', new Date(), "NOT_ENOUGH_INPUT", 2, 2, 2, null, group);
		transition = new Transition(1, "NOT_ENOUGH_INPUT", new Date(), student);
		writtenWord = new WrittenWord(1, "palavra alterada", "palavra errada alterada", "alterado", "NOT_ENOUGH_INPUT", new Date(), student);
		note = new Note(1, "Nota Alterada", new Date(), teacher, student);
		rt = new ReportTeacher(1, 2, 2, 2, 2, 2, 2, 2, teacher);
		rs = new ReportStudent(1, new Date(), 2, teacher, student);
	}
	
	public void testAllPossibleNull() {
		testExistent();
		
		schoolData = new SchoolData("12345678", "Escola Teste");
		school = new School(schoolData, "1234567890", "12345678", null);
		teacher = new Teacher(1, "Professor Teste","1234", "professor@email.com", null, null, null, school);
		group = new Group(1, "Turma Teste", "Serie Teste", 'M', teacher, null);
		student = new Student(1, "Aluno Teste", 'M', new Date(), "NOT_ENOUGH_INPUT", null, null, null, null, group);
		transition = new Transition(1, "NOT_ENOUGH_INPUT", new Date(), student);
		writtenWord = new WrittenWord(1, "palavra teste", "palavra errada", "akljsdhflkahsdf", "NOT_ENOUGH_INPUT", new Date(), student);
		note = new Note(1, "Nota Teste", new Date(), teacher, student);
		rt = new ReportTeacher(1, null, null, null, null, null, null, null, teacher);
		rs = new ReportStudent(1, null, null, null, student);
	}

	public SchoolData getSchoolData() {
		return schoolData;
	}

	public void setSchoolData(SchoolData schoolData) {
		this.schoolData = schoolData;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Transition getTransition() {
		return transition;
	}

	public void setTransition(Transition transition) {
		this.transition = transition;
	}

	public WrittenWord getWrittenWord() {
		return writtenWord;
	}

	public void setWrittenWord(WrittenWord writtenWord) {
		this.writtenWord = writtenWord;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public ReportTeacher getRt() {
		return rt;
	}

	public void setRt(ReportTeacher rt) {
		this.rt = rt;
	}

	public ReportStudent getRs() {
		return rs;
	}

	public void setRs(ReportStudent rs) {
		this.rs = rs;
	}
	
	
}
