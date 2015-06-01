package br.com.farofa.gm.bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.model.UploadedFile;

import br.com.farofa.gm.dao.GroupDAO;
import br.com.farofa.gm.dao.GroupDAOImpl;
import br.com.farofa.gm.dao.SchoolDAO;
import br.com.farofa.gm.dao.SchoolDAOImpl;
import br.com.farofa.gm.dao.SchoolDataDAO;
import br.com.farofa.gm.dao.SchoolDataDAOImpl;
import br.com.farofa.gm.dao.StudentDAO;
import br.com.farofa.gm.dao.StudentDAOImpl;
import br.com.farofa.gm.dao.TeacherDAO;
import br.com.farofa.gm.dao.TeacherDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.manager.Enviroment;
import br.com.farofa.gm.model.Group;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.SchoolData;
import br.com.farofa.gm.model.Student;
import br.com.farofa.gm.model.Teacher;
import br.com.farofa.gm.util.DateConverterUtil;
import br.com.farofa.gm.util.SyncCodeGeneratorUtil;

@ManagedBean
public class GeralImportBean {
	private SchoolDataDAO schoolDataDAO;
	private SchoolDAO schoolDAO;
	private TeacherDAO teacherDAO;
	private GroupDAO groupDAO;
	private StudentDAO studentDAO;
	
	private boolean rendered;
	private List<School> schoolsAdded;
	private UploadedFile uploadedFile;
	
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
    	schoolDataDAO = new SchoolDataDAOImpl(DataBaseManager.getEntityManager());
    	schoolDAO = new SchoolDAOImpl(DataBaseManager.getEntityManager());
    	teacherDAO = new TeacherDAOImpl(DataBaseManager.getEntityManager());
    	groupDAO = new GroupDAOImpl(DataBaseManager.getEntityManager());
    	studentDAO = new StudentDAOImpl(DataBaseManager.getEntityManager());
    	
    	if (uploadedFile != null) {
    		try {
    			proccessExcel(uploadedFile);
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso!", "O arquivo " + uploadedFile.getFileName() + " foi adicionado com sucesso."));
    		}catch(Exception e){
    			e.printStackTrace();
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro!", "Erro no envio do Excel!"));
    		}
    	}else{
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro!", "Não foi possível concluir o envio do arquivo!"));
        }
    	
    	DataBaseManager.close();
    }
    
    private void proccessExcel(UploadedFile uploadedFile) throws Exception{
    	//Declaration of lines as objects
    	Map<String, SchoolData> schoolDataMap = new HashMap<String, SchoolData>();
    	Map<String, School> schoolMap = new HashMap<String, School>();
    	Map<String, Teacher> teacherMap = new HashMap<String, Teacher>();
    	Map<String, Group> groupMap = new HashMap<String, Group>();
    	List<Student> studentList = new ArrayList<Student>();
		
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
			String codigoInepDaEscola = null;
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
						codigoInepDaEscola = String.valueOf((int)cell.getNumericCellValue());
				 	}else if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
			 			codigoInepDaEscola = cell.getStringCellValue();
					}
					
					//Verifica se é base teste ou produção
					if(DataBaseManager.getEnviroment() == Enviroment.banco_teste2.name()){
						try {
							nomeDaEscola = getSchoolNameByInep(codigoInepDaEscola);
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}
					}else{
						nomeDaEscola = "Escola Teste";
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
					}else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						if(HSSFDateUtil.isCellDateFormatted(cell)){
							dataDeNascimento = new SimpleDateFormat("ddMMyyyy").format(cell.getDateCellValue());				
						}else{
							dataDeNascimento = String.valueOf((int)cell.getNumericCellValue());
						}
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
			if(sexo.equals("Menina"))
				sexoChar = 'F';
			
			//Validate data nascimento
			Date date = DateConverterUtil.stringToDate(dataDeNascimento);
			
			//Settando Keys
			String sdKey = codigoInepDaEscola + "-" + nomeDaEscola;
			String schoolKey = sdKey;
			String teacherKey = professor + "-" + emailProfessor + "-" + codigoInepDaEscola;
			String groupKey = nomeDaTurma + "-" + serie + "-" + periodoChar + "-" + codigoInepDaEscola;
			
			//Declara objetos
			SchoolData sd = null;
			School school = null;
			Teacher teacher = null;
			Group group = null;
			
			//SchoolDataMap
			if(!schoolDataMap.containsKey(sdKey)){
				sd = new SchoolData(codigoInepDaEscola, nomeDaEscola);
				schoolDataMap.put(sdKey, sd);
			}else{
				sd = schoolDataMap.get(sdKey);
			}
			//SchoolMap
			if(!schoolMap.containsKey(schoolKey)){
				school = new School(sd, null, null, null);
				schoolMap.put(schoolKey, school);
			}else{
				school = schoolMap.get(schoolKey);
			}
			//TeacherMap
			if(!teacherMap.containsKey(teacherKey)){
				teacher = new Teacher(null, professor, null, emailProfessor, null, null, null, school);
				teacherMap.put(teacherKey, teacher);
			}else{
				teacher = teacherMap.get(teacherKey);
			}
			//GroupMap
			if(!groupMap.containsKey(groupKey)){
				group = new Group(null, nomeDaTurma, serie, periodoChar, teacher, null);
				groupMap.put(groupKey, group);
			}else{
				group = groupMap.get(groupKey);
			}
			
			//StudentList
			Student student = new Student(null, nomeCompletoDoAluno, sexoChar, date, null, null, null, null, null, group);
			studentList.add(student);
		}
		
		
		//Start Updating de DataBase
		
		//School Data
		for (SchoolData sd : schoolDataMap.values()){
			SchoolData tmp = schoolDataDAO.findById(sd.getInep());
			if (tmp != null){
				sd.setName(tmp.getName());
			}else{
				schoolDataDAO.save(sd);
			}
		}
		
		//School
		for (School school : schoolMap.values()){
			School tmp = schoolDAO.findById(school.getSchoolData().getInep());
			if(tmp==null){
				school.setSync_code(SyncCodeGeneratorUtil.generate());
				school.setPassword("12345678");
				schoolDAO.save(school);
				//Mostra na tela
				schoolsAdded.add (school);
			}
		}
		
		//Teacher
		for (Teacher teacher : teacherMap.values()){
			Teacher tmp = teacherDAO.findByNameAndInep(teacher.getName(), teacher.getSchool().getSchoolData().getInep());
			if(tmp != null){
				teacher.setId(tmp.getId());
			}else{
				teacher.setPassword("1234");
				teacherDAO.save(teacher);
			}
		}
		
		//Group
		for (Group group : groupMap.values()){
			Group tmp = groupDAO.findByNameAndSerieAndPeriodAndInep(group.getName(), group.getSerie(), group.getPeriod(), 
					group.getTeacher().getSchool().getSchoolData().getInep());
			if(tmp != null){
				group.setId(tmp.getId());
			}else{
				groupDAO.save(group);
			}
		}
		
		//Student
		for (Student student : studentList){
			student.setDiagnosis_level("NOT_ENOUGH_INPUT");
			studentDAO.save(student);
		}
	}
    
	private String getSchoolNameByInep (String inep) throws Exception {
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