package br.com.motogatomanager.bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
    			Workbook myWorkBook = WorkbookFactory.create(uploadedFile.getInputstream());
    			
    			// Return first sheet from the XLSX workbook
    			Sheet mySheet = myWorkBook.getSheetAt(0);
    			
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
    				
    				int inep_code = 0;
    				String schoolName = null;
    				String studentGroupName = null;
    				String serie = null;
    				String period = null;
    				String teacherName = null;
    				String teacherEmail = null;
    				String studentName = null;
    				String birthDate = null;
    				String gender = null;
    				
    				// For each row, iterate through each columns
    				Iterator<Cell> cellIterator = row.cellIterator();
    				while (cellIterator.hasNext()) {
    					
    					Cell cell = cellIterator.next();
    					int columnIndex = cell.getColumnIndex();
    					
    					if (columnIndex == 0) {
    						if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
    							inep_code = (int) cell.getNumericCellValue();
    					 	}else if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
    					 		try{
    					 			inep_code = Integer.parseInt(cell.getStringCellValue());
    					 		}catch(NumberFormatException nfe){
    					 			System.out.println("Não foi preenchido o código INEP da escola!");
    					 		}
    						}
    						
    						try {
    							schoolName = getSchoolNameByInep(inep_code);
							} catch (Exception e) {
								return;
							}
    						
    						//schoolName = cell.getStringCellValue().trim();
    					} else if (columnIndex == 1) {
    						studentGroupName = cell.getStringCellValue().trim();
    					} else if (columnIndex == 2) {
    						serie = cell.getStringCellValue().trim();
    					} else if (columnIndex == 3) {
    						period = cell.getStringCellValue().trim();
    					} else if (columnIndex == 4) {
    						teacherName = cell.getStringCellValue().trim();
    					} else if (columnIndex == 5) {
    						teacherEmail = cell.getStringCellValue().trim();
    					} else if (columnIndex == 6) {
    						studentName = cell.getStringCellValue().trim();
    					} else if (columnIndex == 7) {
    						if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
    							birthDate = cell.getStringCellValue();
    						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
    							birthDate = String.valueOf((int)cell.getNumericCellValue());
    						}
    					} else if (columnIndex == 8) {
    						gender = cell.getStringCellValue().trim();
    					}
    				}
    				
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
		
		if (schoolName == null || schoolName.equals(""))
			return;
		if (studentGroupName == null || serie == null || period == null)
			return;
		if (teacherName == null || teacherEmail == null)
			return;
		if (studentName == null || birthDate == null || gender == null)
			return;
		
		//School
		//String schoolName = schoolCell.getContents();
		if (!schoolNameSet.contains(schoolName)) {
			school = new SchoolDAO ().fetchByName(schoolName);
			if (school == null) {
				
				school = new School (schoolName);
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
		String teacherKey = teacherName + "-" + school.getId();
		
		if (!teacherFullNameSet.contains(teacherKey)) {
			teacher = new TeacherDAO ().fetchByNameAndLastNameAndSchool(firstName, lastName, school);
			if (teacher == null) {
				teacher = new Teacher (firstName, lastName, "1234", teacherEmail, "", "", school);
				new TeacherDAO ().save(teacher);
			}
		}
		teacherFullNameSet.add(teacherKey);
		
		//Student Group
		String groupKey = studentGroupName + "-" + serie + "-" + period + "-" + school.getId();
		
		if (!groupSerieSet.contains(groupKey)) {
			group = new StudentGroupDAO ().fetchByNameAndSerieAndSchool(studentGroupName, serie, school);
			if (group == null) {
				group = new StudentGroup (studentGroupName, serie, period, school);
				new StudentGroupDAO ().save(group);
			}
		}
		groupSerieSet.add(groupKey);
		
		//StudentGroup_Teacher
		String teacherId = String.valueOf (teacher.getId());
		String groupId = String.valueOf(group.getId());
		String sg_tKey = teacherId + "-" + groupId + "-" + school.getId();
		
		if (!sg_tSet.contains(sg_tKey)) {
			sg_t = new StudentGroup_TeacherDAO().fetchByTeacherAndGroupAndSchool(teacher, group, school);
			if (sg_t == null) {
				sg_t = new StudentGroup_Teacher(school, group, teacher);
				new StudentGroup_TeacherDAO().save(sg_t); 
			}
		}
		sg_tSet.add(sg_tKey);
		
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
			
		Student student = new Student (firstNameAluno, lastNameAluno, gender, date, "NOT_ENOUGH_INPUT", 0, 0, school, group);
		new StudentDAO ().save(student);
    }
    
	private String getSchoolNameByInep (int inep) throws Exception {
    	String url = "http://www.fnde.gov.br/pddeinfo/index.php/pddeinfo/escola/consultar";
   	 
		URL obj = new URL(url);
		URLConnection con = obj.openConnection();
		
		con.setDoOutput(true);
		con.setDoInput(true);
		
		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		wr.write("codescola=" + inep);
		wr.flush();
		
 
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		//System.out.println(response.toString());
		
		String result = null;
		try{
			result = response.toString().split("<th>Nome Escola:</th><td colspan=5>")[1].split("</td></tr><tr><th>UF:</th>")[0];
			System.out.println(result);
		}catch (Exception e){
			System.out.println("INEP inexistente");
		}
		
		return result;
    }
    
    
    //Getters and Setters 
    
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
