package br.com.motogatomanager.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.UploadedFile;

import br.com.motogatomanager.dao.SchoolDAO;
import br.com.motogatomanager.dao.StudentDAO;
import br.com.motogatomanager.dao.StudentGroupDAO;
import br.com.motogatomanager.dao.StudentGroup_TeacherDAO;
import br.com.motogatomanager.dao.TeacherDAO;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.Student;
import br.com.motogatomanager.modelo.StudentGroup;
import br.com.motogatomanager.modelo.StudentGroup_Teacher;
import br.com.motogatomanager.modelo.Teacher;

@ManagedBean
public class GeralImportBean {
	
	private boolean rendered;
	private List<School> schoolsAdded;
	private Random rand = new Random();
	private UploadedFile uploadedFile;
	
	private School school;
	private Teacher teacher;
	private StudentGroup group;
	private StudentGroup_Teacher sg_t;
	
	private Set<String> schoolNameSet;
	private Set<String> teacherFullNameSet;
	private Set<String> groupSerieSet;
	private Set<String> sg_tSet;
	
	@PostConstruct
	public void init () {
		schoolsAdded = new ArrayList<School>();
	}
	
	public String access() {
		return "geralImport";
	}
	
	public String back() {
		return "/home";
	}
	
	 
    public UploadedFile getFile() {
        return uploadedFile;
    }
 
    public void setFile(UploadedFile file) {
        this.uploadedFile = file;
    }
    
    public void upload () {
    	if (uploadedFile != null) {
    		try {
    			// Finds the workbook instance for XLSX file
    			@SuppressWarnings("resource")
    			XSSFWorkbook myWorkBook = new XSSFWorkbook(uploadedFile.getInputstream());
    			
    			// Return first sheet from the XLSX workbook
    			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
    			
    			// Get iterator to all the rows in current sheet
    			Iterator<Row> rowIterator = mySheet.iterator();
    			
    			schoolNameSet = new HashSet<String> ();
    			teacherFullNameSet = new HashSet<String> ();
    			groupSerieSet = new HashSet<String> ();
    			sg_tSet = new HashSet<String>();
    			
    			// Traversing over each row of XLSX file
    			rowIterator.next();
    			while (rowIterator.hasNext()) {
    				//Pula o header
    				Row row = rowIterator.next();
    				
    				String schoolName = null;
    				String studentGroupName = null;
    				String serie = null;
    				String period = null;
    				String teacherName = null;
    				String teacherEmail = null;
    				String studentName = null;
    				String birthDate = null;
    				String gender = null;
    				
    				boolean linhaAMais = false;
    				
    				// For each row, iterate through each columns
    				Iterator<Cell> cellIterator = row.cellIterator();
    				while (cellIterator.hasNext()) {
    					
    					Cell cell = cellIterator.next();
    					int columnIndex = cell.getColumnIndex();
    					
    					if (columnIndex == 0) {
    						schoolName = cell.getStringCellValue();
    					} else if (columnIndex == 1) {
    						studentGroupName = cell.getStringCellValue();
    					} else if (columnIndex == 2) {
    						serie = cell.getStringCellValue();
    					} else if (columnIndex == 3) {
    						period = cell.getStringCellValue();
    					} else if (columnIndex == 4) {
    						teacherName = cell.getStringCellValue();
    					} else if (columnIndex == 5) {
    						teacherEmail = cell.getStringCellValue();
    					} else if (columnIndex == 6) {
    						studentName = cell.getStringCellValue();
    					} else if (columnIndex == 7) {
    						birthDate = String.valueOf((int)cell.getNumericCellValue());
    					} else if (columnIndex == 8) {
    						gender = cell.getStringCellValue();
    					}
    					
    					if (columnIndex == 9) {
    						System.out.println("Excel tem linha de mais de 9 colunas!");
    						linhaAMais = true;
    					}
    				}
    				
    				if (!linhaAMais) {
    					System.out.print(schoolName + "\t");
    					System.out.print(studentGroupName + "\t");
    					System.out.print(serie + "\t");
    					System.out.print(period + "\t");
    					System.out.print(teacherName + "\t");
    					System.out.print(teacherEmail + "\t");
    					System.out.print(studentName + "\t");
    					System.out.print(birthDate + "\t");
    					System.out.print(gender + "\t");
    					System.out.println();
    					
    					tratarLinha(schoolName, studentGroupName, serie, period, teacherName, teacherEmail, studentName, birthDate, gender);
    				}
    				
    				linhaAMais = false;
    			}
    			
    			FacesMessage message = new FacesMessage("Sucesso!", uploadedFile.getFileName() + " foi adicionado.");
    			FacesContext.getCurrentInstance().addMessage(null, message);
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    			FacesMessage message = new FacesMessage("Erro!", e.toString());
    			FacesContext.getCurrentInstance().addMessage(null, message);
    		}
    	}
    }
    
    public void tratarLinha (String schoolName, String studentGroupName, String serie, String period, String teacherName, 
		   String teacherEmail, String studentName, String birthDate, String gender) throws ParseException {
		
		if (schoolName.equals(""))
			return;
		
		//School
		//String schoolName = schoolCell.getContents();
		if (!schoolNameSet.contains(schoolName)) {
			school = new SchoolDAO ().fetchByName(schoolName);
			if (school.getId() == 0) {
				String sync_code = String.valueOf(10000000 + rand.nextInt(90000000));
				school = new School (schoolName, sync_code);
				new SchoolDAO().save(school);
				
				teacherFullNameSet = new HashSet<String> ();
		    	groupSerieSet = new HashSet<String> ();
		    	sg_tSet = new HashSet<String>();
		    	schoolsAdded.add (school);
			}
		}
		schoolNameSet.add(schoolName);
		
		//Teacher
		String firstName = teacherName.split(" ")[0];
		String lastName = teacherName.split(" ").length > 1 ? teacherName.substring(teacherName.indexOf(' ')+1).trim() : "";
		
		if (!teacherFullNameSet.contains(teacherName)) {
			teacher = new TeacherDAO ().fetchByNameAndLastNameAndSchool(firstName, lastName, school);
			if (teacher.getId() == 0) {
				teacher = new Teacher (firstName, lastName, "1234", teacherEmail, "", "", school);
				new TeacherDAO ().save(teacher);
			}
		}
		teacherFullNameSet.add(teacherName);
		
		//Student Group
		String keyGroup = studentGroupName + "-" + serie + "-" + period;
		
		if (!groupSerieSet.contains(keyGroup)) {
			group = new StudentGroupDAO ().fetchByNameAndSerieAndSchool(studentGroupName, serie, school);
			if (group.getId() == 0) {
				group = new StudentGroup (studentGroupName, serie, period, school);
				new StudentGroupDAO ().save(group);
			}
		}
		groupSerieSet.add(keyGroup);
		
		//StudentGroup_Teacher
		String teacherId = String.valueOf (teacher.getId());
		String groupId = String.valueOf(group.getId());
		String keySG_T = teacherId + "-" + groupId;
		
		if (!sg_tSet.contains(keySG_T)) {
			sg_t = new StudentGroup_TeacherDAO().fetchByTeacherAndGroupAndSchool(teacher, group, school);
			if (sg_t.getId() == 0) {
				sg_t = new StudentGroup_Teacher(school, group, teacher);
				new StudentGroup_TeacherDAO().save(sg_t); 
			}
		}
		sg_tSet.add(keySG_T);
		
		//Student
		String firstNameAluno = studentName.split(" ")[0];
		String lastNameAluno = studentName.split(" ").length > 1 ? studentName.substring(studentName.indexOf(' ')+1) : "";
		Date date = null;
		if (birthDate.length() == 8) {
			date = new SimpleDateFormat("ddMMyyyy").parse(birthDate);
		} else if (birthDate.length() == 7) {
			date = new SimpleDateFormat("dMMyyyy").parse(birthDate);
		} else {
			return;
		}
			
		Student student = new Student (firstNameAluno, lastNameAluno, gender, date, "NOT_ENOUGH_INPUT", 0, 0, school, group);
		new StudentDAO ().save(student);
    }

	public List<School> getSchoolsAdded() {
		return schoolsAdded;
	}

	public void setSchoolsAdded(List<School> schoolsAdded) {
		this.schoolsAdded = schoolsAdded;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}
	
}
