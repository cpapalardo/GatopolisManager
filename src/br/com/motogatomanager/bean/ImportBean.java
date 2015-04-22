package br.com.motogatomanager.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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

import br.com.motogatomanager.dao.StudentDAO;
import br.com.motogatomanager.dao.StudentGroupDAO;
import br.com.motogatomanager.dao.StudentGroup_TeacherDAO;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.Student;
import br.com.motogatomanager.modelo.StudentGroup;
import br.com.motogatomanager.modelo.StudentGroup_Teacher;
import br.com.motogatomanager.modelo.Teacher;

@ManagedBean
public class ImportBean {
	private School school;
	private Teacher teacher;
	
	private StudentGroup group;
	private StudentGroup_Teacher sg_t;
	
	private Set<String> groupSet;
	private Set<String> sg_tSet;
	
	@PostConstruct
	public void init() {
		school = (School) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("school");
		teacher = (Teacher) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("teacher");
	}
	
	public String toImport () {
		return "import";
	}
	
	public String back () {
		return "teachers";
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
        	try {
        		// Finds the workbook instance for XLSX file
    			@SuppressWarnings("resource")
    			XSSFWorkbook myWorkBook = new XSSFWorkbook(uploadedFile.getInputstream());
    			
    			// Return first sheet from the XLSX workbook
    			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
    			
    			// Get iterator to all the rows in current sheet
    			Iterator<Row> rowIterator = mySheet.iterator();
    			
    			groupSet = new HashSet<String> ();
    			sg_tSet = new HashSet<String>();
    			
    			// Traversing over each row of XLSX file
    			rowIterator.next();
    			while (rowIterator.hasNext()) {
    				//Pula o header
    				Row row = rowIterator.next();
					
					String studentName = null;
					Date birthDate = null;
					String gender = null;
					String groupName = null;
					String period = null;
					String serie = null;
					
					boolean linhaAMais = false;
					
					// For each row, iterate through each columns
					Iterator<Cell> cellIterator = row.cellIterator();
    				while (cellIterator.hasNext()) {
    					Cell cell = cellIterator.next();
						int columnIndex = cell.getColumnIndex();
    					
    					if (columnIndex == 0) {
    						studentName = cell.getStringCellValue();
    					} else if (columnIndex == 1) {
    						birthDate = cell.getDateCellValue();
    					} else if (columnIndex == 2) {
    						gender = cell.getStringCellValue();
    					} else if (columnIndex == 3) {
    						groupName = cell.getStringCellValue();
    					} else if (columnIndex == 4) {
    						period = cell.getStringCellValue();
    					} else if (columnIndex == 5) {
    						serie = cell.getStringCellValue();
    					}
    					
    					if (columnIndex == 6) {
    						System.out.println("Excel tem linha de mais de 6 colunas!");
    						linhaAMais = true;
    					}
    				}
    				
    				if (!linhaAMais) {
    					System.out.print(studentName + "\t");
    					System.out.print(birthDate + "\t");
    					System.out.print(gender + "\t");
    					System.out.print(groupName + "\t");
    					System.out.print(period + "\t");
    					System.out.print(serie + "\t");
    					System.out.println();
    					
    					tratarLinha(groupName, serie, period, studentName, birthDate, gender);
    				}
    				
    				linhaAMais = false;
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
    
    public void tratarLinha (String groupName, String serie, String period, String studentName, Date birthDate, String gender) {
    	
    	
    	//Student Group
		String keyGroup = groupName + "-" + serie + "-" + period;
		
		if (!groupSet.contains(keyGroup)) {
			group = new StudentGroupDAO ().fetchByNameAndSerieAndSchool(groupName, serie, school);
			if (group.getId() == 0) {
				group = new StudentGroup (groupName, serie, period, school);
				new StudentGroupDAO ().save(group);
			}
		}
		groupSet.add(keyGroup);
		
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
			
		Student student = new Student (firstNameAluno, lastNameAluno, gender, birthDate, "NOT_ENOUGH_INPUT", 0, 0, school, group);
		new StudentDAO ().save(student);
    }
	
}
