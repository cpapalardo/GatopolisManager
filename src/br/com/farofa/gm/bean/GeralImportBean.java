package br.com.farofa.gm.bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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

import br.com.farofa.gm.dao.GroupDAOImpl;
import br.com.farofa.gm.dao.SchoolDAOImpl;
import br.com.farofa.gm.dao.SchoolDataDAOImpl;
import br.com.farofa.gm.dao.StudentDAOImpl;
import br.com.farofa.gm.dao.TeacherDAOImpl;
import br.com.farofa.gm.model.Group;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.SchoolData;
import br.com.farofa.gm.model.Student;
import br.com.farofa.gm.model.Teacher;

@ManagedBean
public class GeralImportBean {
	
	private boolean rendered;
	private List<School> schoolsAdded;
	private UploadedFile uploadedFile;
	
	//Usado para tratar linhas do excel
	private HashMap<String, SchoolData> schoolDataMap;
	private HashMap<String, School> schoolMap;
	private HashMap<String, Teacher> teacherMap;
	private HashMap<String, Group> groupMap;
	
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
    			ListaAlunosExcelItem item = getListAlunosExcelItem(uploadedFile);
    			
    			if(item != null) {
    				
    				schoolDataMap = new HashMap<String, SchoolData>();
    				schoolMap = new HashMap<String, School>();
    				teacherMap = new HashMap<String, Teacher>();
    				groupMap = new HashMap<String, Group>();
    				
    				listaAlunosTratarLinha(item);
    			}
    			
    		}catch(Exception e){
    			e.printStackTrace();
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro!", e.toString()));
    		}
    		
			
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso!", uploadedFile.getFileName() + " foi adicionado."));
    	}
    }
    
    private ListaAlunosExcelItem getListAlunosExcelItem(UploadedFile uploadedFile) throws Exception{
		ListaAlunosExcelItem item = null;
		
		// Finds the workbook instance for XLSX file
		Workbook myWorkBook = WorkbookFactory.create(uploadedFile.getInputstream());
		
		// Return first sheet from the XLSX workbook
		Sheet mySheet = myWorkBook.getSheetAt(0);
		
		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = mySheet.iterator();
		
		// Traversing over each row of XLSX file
		rowIterator.next();
		while (rowIterator.hasNext()) {
			//Pula o header
			Row row = rowIterator.next();
			
			//Declaração dos campos do excel
			int codigoInepDaEscola = 0;
			String nomeDaTurma = null;
			String serie = null;
			String periodo = null;
			String professor = null;
			String emailProfessor = null;
			String nomeCompletoDoAluno = null;
			String dataDeNascimento = null;
			String sexo = null;
			
			String nomeDaEscola = null;
			
			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				
				Cell cell = cellIterator.next();
				int columnIndex = cell.getColumnIndex();
				
				//Código inep da escola
				if (columnIndex == 0) {
					if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						codigoInepDaEscola = (int) cell.getNumericCellValue();
				 	}else if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
				 		try{
				 			codigoInepDaEscola = Integer.parseInt(cell.getStringCellValue());
				 		}catch(NumberFormatException nfe){
				 			System.out.println("Não foi preenchido o código INEP da escola!");
				 		}
					}
					
					try {
						nomeDaEscola = getSchoolNameByInep(codigoInepDaEscola);
					} catch (Exception e) {
						continue;
					}
				} 
				//Nome da turma
				else if (columnIndex == 1) {
					nomeDaTurma = cell.getStringCellValue().trim();
				} 
				//Serie
				else if (columnIndex == 2) {
					serie = cell.getStringCellValue().trim();
				} 
				//Periodo
				else if (columnIndex == 3) {
					periodo = cell.getStringCellValue().trim();
				} 
				//Professor
				else if (columnIndex == 4) {
					professor = cell.getStringCellValue().trim();
				} 
				//Email professor
				else if (columnIndex == 5) {
					emailProfessor = cell.getStringCellValue().trim();
				} 
				//Nome completo do aluno
				else if (columnIndex == 6) {
					nomeCompletoDoAluno = cell.getStringCellValue().trim();
				} 
				//Data de nascimento
				else if (columnIndex == 7) {
					if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
						dataDeNascimento = cell.getStringCellValue();
					} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						dataDeNascimento = String.valueOf((int)cell.getNumericCellValue());
					}
				} 
				//Sexo
				else if (columnIndex == 8) {
					sexo = cell.getStringCellValue().trim();
				}
			}
			
			System.out.print(codigoInepDaEscola + "\t");
			System.out.print(nomeDaTurma + "\t");
			System.out.print(serie + "\t");
			System.out.print(periodo + "\t");
			System.out.print(professor + "\t");
			System.out.print(emailProfessor + "\t");
			System.out.print(nomeCompletoDoAluno + "\t");
			System.out.print(dataDeNascimento + "\t");
			System.out.print(sexo + "\t");
			System.out.println();
			
			//Validate periodo
			char periodoChar = 'M';
			if(periodo == "Tarde")
				periodoChar = 'T';
			else if(periodo == "Integral")
				periodoChar = 'I';
			
			//Validate sexo
			char sexoChar = 'M';
			if(sexo == "Menina")
				sexoChar = 'F';
			
			//Validate data nascimento
			Date date = null;
			if (dataDeNascimento.length() == 8) {
				date = new SimpleDateFormat("ddMMyyyy").parse(dataDeNascimento);
			} else if (dataDeNascimento.length() == 7) {
				date = new SimpleDateFormat("dMMyyyy").parse(dataDeNascimento);
			} else if (dataDeNascimento.length() == 10) {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(dataDeNascimento);
			} else {
				date = new Date();
			}
			
			//Criar objetos
			SchoolData sd = new SchoolData(codigoInepDaEscola, nomeDaEscola);
			School school = new School(sd, null, null, null);
			Teacher teacher = new Teacher(null, professor, 1234, emailProfessor, null, null, null, school);
			Group group = new Group(null, nomeDaTurma, serie, periodoChar, teacher, 0);
			Student student = new Student(null, nomeCompletoDoAluno, sexoChar, date, "NOT_ENOUGH_INPUT", null, null, null, null, group);
			
			//Settando Keys
			String sdKey = sd.getInep() + "-" + sd.getName();
			String schoolKey = String.valueOf(school.getSync_code());
			String teacherKey = teacher.getName() + "-" + teacher.getEmail();
			String groupKey = group.getName() + "-" + group.getSerie() + "-" + group.getPeriod();
			String studentKey = student.getName() + "-" + student.getGender() + "-" + (new SimpleDateFormat("dd/MM/yyyy").format(student.getBirth_date()));
			
			//Criar resultado de retorno
			item = new ListaAlunosExcelItem();
			item.setSchoolData(sd);
			item.setSchool(school);
			item.setTeacher(teacher);
			item.setGroup(group);
			item.setStudent(student);
	    	
	    	item.setSchoolDataKey(sdKey);
	    	item.setSchoolKey(schoolKey);
	    	item.setTeacherKey(teacherKey);
	    	item.setGroupKey(groupKey);
	    	item.setStudentKey(studentKey);
		}
		
		return item;
	}
    
    private void listaAlunosTratarLinha (ListaAlunosExcelItem item) throws ParseException {
		//School Data
    	String sdKey = item.getSchoolDataKey();
    	SchoolData sd = null;
		if (!schoolDataMap.containsKey(sdKey)){
			sd = new SchoolDataDAOImpl().findById(item.getSchoolData().getInep());
			if (sd == null){
				sd = item.getSchoolData();
				new SchoolDataDAOImpl().save(sd);
			}
		}else{
			sd = schoolDataMap.get(sdKey);
		}
		schoolDataMap.put(sdKey, sd);
		
		//School
		String schoolKey = item.getSchoolKey();
		School school = null;
		if (!schoolMap.containsKey(schoolKey)){
			school = new SchoolDAOImpl().findByName(item.getSchool().getSchoolData().getName());
			if (school == null) {
				school = item.getSchool();
				new SchoolDAOImpl().save(school);
		    	schoolsAdded.add (school);
			}
		}else{
			school = schoolMap.get(schoolKey);
		}
		schoolMap.put(schoolKey, school);
		
		//Teacher
		String teacherKey = item.getTeacherKey();
		Teacher teacher = null;
		if (!teacherMap.containsKey(teacherKey)) {
			teacher = new TeacherDAOImpl ().findByNameAndInep(item.getTeacher().getName(), item.getSchoolData().getInep());
			if (teacher == null) {
				teacher = item.getTeacher();
				new TeacherDAOImpl().save(teacher);
			}
		}else{
			teacher = teacherMap.get(teacherKey);
		}
		teacherMap.put(teacherKey, teacher);
		
		//Group
		String groupKey = item.getGroupKey();
		Group group = null;
		if (!groupMap.containsKey(groupKey)) {
			group = new GroupDAOImpl().findByNameAndSerieAndPeriodAndInep(item.getGroup().getName(), item.getGroup().getSerie(), 
					item.getGroup().getPeriod(), item.getSchoolData().getInep());
			if (group == null) {
				group = item.getGroup();
				new GroupDAOImpl().save(group);
			}
		}
		groupMap.put(groupKey, group);
		
		//Student
		Student student = item.getStudent();
		new StudentDAOImpl().save(student);
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

class ListaAlunosExcelItem {
	private String schoolDataKey;
	private String schoolKey;
	private String teacherKey;
	private String groupKey;
	private String studentKey;
	
	private SchoolData schoolData;
	private School school;
	private Teacher teacher;
	private Group group;
	private Student student;
	
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
	public String getSchoolDataKey() {
		return schoolDataKey;
	}
	public void setSchoolDataKey(String sdKey) {
		this.schoolDataKey = sdKey;
	}
	public String getSchoolKey() {
		return schoolKey;
	}
	public void setSchoolKey(String schoolKey) {
		this.schoolKey = schoolKey;
	}
	public String getTeacherKey() {
		return teacherKey;
	}
	public void setTeacherKey(String teacherKey) {
		this.teacherKey = teacherKey;
	}
	public String getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}
	public String getStudentKey() {
		return studentKey;
	}
	public void setStudentKey(String studentKey) {
		this.studentKey = studentKey;
	}
}
