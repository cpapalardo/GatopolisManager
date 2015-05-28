package br.com.farofa.gm.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.model.UploadedFile;

import br.com.farofa.gm.model.Group;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.Teacher;

@ManagedBean
public class ImportBean {
	private School school;
	private Teacher teacher;
	
	private Group group;
	
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
    			Workbook myWorkBook = WorkbookFactory.create(uploadedFile.getInputstream());
    			
    			// Return first sheet from the XLSX workbook
    			Sheet mySheet = myWorkBook.getSheetAt(0);
    			
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
					String birthDate = null;
					String gender = null;
					String groupName = null;
					String period = null;
					String serie = null;
					
					// For each row, iterate through each columns
					Iterator<Cell> cellIterator = row.cellIterator();
    				while (cellIterator.hasNext()) {
    					Cell cell = cellIterator.next();
						int columnIndex = cell.getColumnIndex();
    					
    					if (columnIndex == 0) {
    						studentName = cell.getStringCellValue();
    					} else if (columnIndex == 1) {
    						if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
    							birthDate = cell.getStringCellValue();
    						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
    							birthDate = String.valueOf((int)cell.getNumericCellValue());
    						}
    					} else if (columnIndex == 2) {
    						gender = cell.getStringCellValue();
    					} else if (columnIndex == 3) {
    						groupName = cell.getStringCellValue();
    					} else if (columnIndex == 4) {
    						period = cell.getStringCellValue();
    					} else if (columnIndex == 5) {
    						serie = cell.getStringCellValue();
    					}
    				}
    				
					System.out.print(studentName + "\t");
					System.out.print(birthDate + "\t");
					System.out.print(gender + "\t");
					System.out.print(groupName + "\t");
					System.out.print(period + "\t");
					System.out.print(serie + "\t");
					System.out.println();
					
					tratarLinha(groupName, serie, period, studentName, birthDate, gender);
    			}
				
				FacesMessage message = new FacesMessage("Sucesso!", uploadedFile.getFileName() + " foi adicionado.");
				FacesContext.getCurrentInstance().addMessage(null, message);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				FacesMessage message = new FacesMessage("Erro!", "Não foi possível concluir o envio!");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
        } else {
        	FacesMessage message = new FacesMessage("Erro!", "Não foi possível concluir o envio do arquivo!");
        	FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void tratarLinha (String groupName, String serie, String period, String studentName, String birthDate, String gender) throws ParseException{
    	
    	if (groupName == null || serie == null || period == null || studentName == null || birthDate == null || gender == null) {
    		return;
    	}
    	
    	//Student Group
		String keyGroup = null;//groupName + "-" + serie + "-" + period + "-" + school.getId();
		
		if (!groupSet.contains(keyGroup)) {
			/*group = new GroupDAOImpl ().findByNameAndSerieAndSchool(groupName, serie, school);
			if (group == null) {
				group = new Group(groupName, serie, period, school);
				new GroupDAOImpl ().save(group);
			}*/
		}
		groupSet.add(keyGroup);
		
		//StudentGroup_Teacher
		String teacherId = String.valueOf (teacher.getId());
		String groupId = String.valueOf(group.getId());
		String keySG_T = null;//teacherId + "-" + groupId + "-" + school.getId();
		
		if (!sg_tSet.contains(keySG_T)) {
			/*sg_t = new StudentGroup_TeacherDAO().fetchByTeacherAndGroupAndSchool(teacher, group, school);
			if (sg_t == null) {
				sg_t = new StudentGroup_Teacher(school, group, teacher);
				new StudentGroup_TeacherDAO().save(sg_t); 
			}*/
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
		} else if (birthDate.length() == 10) {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
		} else {
			throw new RuntimeException("Formato da celula de data incorreta!");
		}
			
		/*Student student = new Student (firstNameAluno, lastNameAluno, gender, date, "NOT_ENOUGH_INPUT", 0, 0, school, group);
		new StudentDAOImpl ().save(student);*/
    }
	
}
