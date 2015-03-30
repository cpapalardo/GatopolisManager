package br.com.motogatomanager.bean;

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
import br.com.motogatomanager.dao.TeacherDAO;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.Student;
import br.com.motogatomanager.modelo.StudentGroup;
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
        		
        		w = Workbook.getWorkbook(uploadedFile.getInputstream());
    			Sheet sheet = w.getSheet(0);
    			
    			School school = null;
    			Teacher teacher = null;
    			StudentGroup studentGroup = null;
    			
    			SchoolDAO schoolDao = new SchoolDAO();
    			TeacherDAO teacherDao = new TeacherDAO();
    			StudentGroupDAO studentGroupDao = new StudentGroupDAO();
    			
    			for (int i = 1; i < sheet.getRows(); i++) {
    				
    				Cell schoolCell = sheet.getCell(0, i);
					Cell classCell = sheet.getCell(1, i);
					Cell periodCell = sheet.getCell(2, i);
					Cell professorCell = sheet.getCell(3, i);
					Cell nameCell = sheet.getCell(4, i);
					Cell birthCell = sheet.getCell(5, i);
					Cell genderCell = sheet.getCell(6, i);
					
					if(school == null || !school.getName().equals(schoolCell.getContents())) {
						school = schoolDao.fetchByName(schoolCell.getContents());
						if(school.getName().isEmpty()) {
							School s = new School();
							s.setName(schoolCell.getContents());
							s.setSync_code(String.valueOf(10000000 + rand.nextInt(90000000)));
							schoolDao.save(s);
							school = schoolDao.fetchByName(schoolCell.getContents());
						}
					}
					if(teacher == null || teacher.getName().equals(professorCell.getContents())) {
						
						String fullName = professorCell.getContents().trim();
						String firstName = fullName.split(" ")[0];
						String lastName = fullName.split(" ").length > 1 ? fullName.substring(fullName.indexOf(' ')+1) : "";
						
						teacher = teacherDao.fetchByNameAndSchool(firstName, lastName, school);
						
						if(teacher.getName().isEmpty()) {
							Teacher t = new Teacher();
							t.setName(firstName);
							t.setLast_name(lastName);
							t.setSchool(school);
							
							teacherDao.save(teacher);
							teacher = teacherDao.fetchByNameAndSchool(firstName, lastName, school);
						}
					}
					
					if(studentGroup == null || 
							studentGroup.getPeriod() != periodCell.getContents() || 
							studentGroup.getSeries() != classCell.getContents()) 
					{
						studentGroup = studentGroupDao.fetchBySchoolAndPeriodAndSeries(school, periodCell.getContents(), classCell.getContents());
					
						if(studentGroup.getPeriod().isEmpty()) {
							StudentGroup s = new StudentGroup();
							s.setPeriod(periodCell.getContents());
							s.setSeries(classCell.getContents());
							s.setSchool(school);
							
							studentGroupDao.save(s);
							studentGroup = studentGroupDao.fetchBySchoolAndPeriodAndSeries(school, periodCell.getContents(), classCell.getContents());
						}
					
					}
					
					
    			}
        		
        		/*ExcelUtil excel = new ExcelUtil();
    			excel.read (uploadedFile.getInputstream());
    			
    			//Salva Turma
    			for (StudentGroup group : excel.getGroups()) {
    				group.setSchool(school);
    				if (group != null && group.getId() == 0)
    					new StudentGroupDAO ().save(group);
    			}
    			
    			//Salva Alunos
    			for (Student student : excel.getStudents()) {
    				student.setSchool(school);
    				new StudentDAO ().save(student);
    			}*/
				
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
