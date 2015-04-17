package br.com.motogatomanager.bean;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

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
import br.com.motogatomanager.util.EncodingUtil;

@ManagedBean
public class GeralImportBean {
	
	private boolean rendered;
	private List<School> schoolsAdded;
	private Random rand = new Random();
	
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
	
	private UploadedFile uploadedFile;
	 
    public UploadedFile getFile() {
        return uploadedFile;
    }
 
    public void setFile(UploadedFile file) {
        this.uploadedFile = file;
    }
     
    
    public void upload() {
    	School school = null;
    	Teacher teacher = null;
    	StudentGroup group = null;
    	StudentGroup_Teacher sg_t = null;
    	
    	Set<String> schoolNameSet = new HashSet<String> ();
    	Set<String> teacherFullNameSet = new HashSet<String> ();
    	Set<String> groupSerieSet = new HashSet<String> ();
    	Set<String> sg_tSet = new HashSet<String>();
    	
        if (uploadedFile != null) {
        	Workbook w;
        	try {
        		InputStream stream = uploadedFile.getInputstream();
        		
        		WorkbookSettings ws = new WorkbookSettings();
        		ws.setEncoding("Cp1252");
        		
        		w = Workbook.getWorkbook(stream, ws);
        		
    			Sheet sheet = w.getSheet(0);
    			
    			
    			
    			for (int i = 1; i < sheet.getRows(); i++) {
    				
    				Cell schoolCell = sheet.getCell(0, i);
					Cell classNameCell = sheet.getCell(1, i);
					Cell serieCell = sheet.getCell(2, i);
					Cell periodCell = sheet.getCell(3, i);
					Cell professorCell = sheet.getCell(4, i);
					Cell professorEmailCell = sheet.getCell(5,i);
					Cell nameCell = sheet.getCell(6, i);
					Cell birthCell = sheet.getCell(7, i);
					Cell genderCell = sheet.getCell(8, i);
					
					if (schoolCell.getContents().equals(""))
						continue;
					
					//School
					String schoolName = schoolCell.getContents();
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
					String fullName = professorCell.getContents();
					String firstName = fullName.split(" ")[0];
					String lastName = fullName.split(" ").length > 1 ? fullName.substring(fullName.indexOf(' ')+1).trim() : "";
					String email = professorEmailCell.getContents();
					
					if (!teacherFullNameSet.contains(fullName)) {
						teacher = new TeacherDAO ().fetchByNameAndLastNameAndSchool(firstName, lastName, school);
						if (teacher.getId() == 0) {
							teacher = new Teacher (firstName, lastName, "1234", email, "", "", school);
							new TeacherDAO ().save(teacher);
						}
					}
					teacherFullNameSet.add(fullName);
					
					//Student Group
					String groupName = classNameCell.getContents();
					String serie = classNameCell.getContents();
					String period = periodCell.getContents();
					String keyGroup = groupName + "-" + serie + "-" + period;
					
					if (!groupSerieSet.contains(keyGroup)) {
						group = new StudentGroupDAO ().fetchByNameAndSerieAndSchool(groupName, serie, school);
						if (group.getId() == 0) {
							group = new StudentGroup (groupName, serie, period, school);
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
					String fullNameAluno = nameCell.getContents();
					String firstNameAluno = fullNameAluno.split(" ")[0];
					String lastNameAluno = fullNameAluno.split(" ").length > 1 ? fullNameAluno.substring(fullNameAluno.indexOf(' ')+1) : "";
					String birthDate = birthCell.getContents();
					String gender = genderCell.getContents();
					Date date = null;
					if (birthDate.length() == 8)
						date = new SimpleDateFormat("ddMMyyyy").parse(birthDate);
					else if (birthDate.length() == 7)
						date = new SimpleDateFormat("dMMyyyy").parse(birthDate);
					Student student = new Student (firstNameAluno, lastNameAluno, gender, date, "NOT_ENOUGH_INPUT", 0, 0, school, group);
					new StudentDAO ().save(student);
    			}
				
				FacesMessage message = new FacesMessage("Sucesso!", uploadedFile.getFileName() + " foi adicionado.");
				FacesContext.getCurrentInstance().addMessage(null, message);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				FacesMessage message = new FacesMessage("Erro!", e.toString());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
        } else {
        	FacesMessage message = new FacesMessage("Erro!", "O Upload n�o foi enviado para o servidor!");
        	FacesContext.getCurrentInstance().addMessage(null, message);
        }
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
