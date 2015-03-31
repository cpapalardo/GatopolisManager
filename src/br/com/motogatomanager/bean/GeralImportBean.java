package br.com.motogatomanager.bean;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;

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
import br.com.motogatomanager.util.ExcelUtil;

@ManagedBean
public class GeralImportBean {

	private Random rand = new Random();
	
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
        if (uploadedFile != null) {
        	Workbook w;
        	try {
        		InputStream stream = uploadedFile.getInputstream();
        		w = Workbook.getWorkbook(stream);
    			Sheet sheet = w.getSheet(0);
    			
    			for (int i = 1; i < sheet.getRows(); i++) {
    				
    				Cell schoolCell = sheet.getCell(0, i);
					Cell classCell = sheet.getCell(1, i);
					Cell periodCell = sheet.getCell(2, i);
					Cell professorCell = sheet.getCell(3, i);
					Cell nameCell = sheet.getCell(4, i);
					Cell birthCell = sheet.getCell(5, i);
					Cell genderCell = sheet.getCell(6, i);
					
					if (schoolCell.getContents().equals(""))
						continue;
					
					//School
					String schoolName = schoolCell.getContents();
					School school = new SchoolDAO().fetchByName(schoolCell.getContents());
					if (school != null && school.getId() == 0) {
						String sync_code = String.valueOf(10000000 + rand.nextInt(90000000));
						school = new School (schoolName, sync_code);
						new SchoolDAO().save(school);
					}
					
					//Teacher
					String fullName = professorCell.getContents();
					String firstName = fullName.split(" ")[0];
					String lastName = fullName.split(" ").length > 1 ? fullName.substring(fullName.indexOf(' ')+1) : "";
					
					Teacher teacher = new TeacherDAO ().fetchByNameAndLastName(firstName, lastName);
					if (teacher != null && teacher.getId() == 0) {
						teacher = new Teacher (firstName, lastName, "1234", "", "", "", school);
						new TeacherDAO ().save(teacher);
					}
					
					//Student Group
					String serie = classCell.getContents();
					String period = periodCell.getContents();
					StudentGroup group = new StudentGroupDAO ().fetchBySerie(serie);
					if (group != null && group.getId() == 0) {
						group = new StudentGroup ("", serie, period, school);
					}
					
					//Student Group Teacher
					StudentGroup_Teacher sg_t = new StudentGroup_TeacherDAO().fetchByTeacherAndGroup(teacher, group);
					if (sg_t == null && sg_t.getId() == 0) {
						sg_t = new StudentGroup_Teacher(school, group, teacher);
						new StudentGroup_TeacherDAO().save(sg_t); 
					}
					
					//Student
					String fullNameAluno = nameCell.getContents();
					String firstNameAluno = fullNameAluno.split(" ")[0];
					String lastNameAluno = fullNameAluno.split(" ").length > 1 ? fullName.substring(fullName.indexOf(' ')+1) : "";
					String birthDate = birthCell.getContents();
					String gender = genderCell.getContents();
					Date date = new SimpleDateFormat().parse(birthDate);
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
        	FacesMessage message = new FacesMessage("Erro!", "O Upload n‹o foi enviado para o servidor!");
        	FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
	
}
