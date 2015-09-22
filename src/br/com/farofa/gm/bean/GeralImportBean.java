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
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.model.UploadedFile;

import br.com.farofa.gm.dao.RoomDAO;
import br.com.farofa.gm.dao.SchoolDAO;
import br.com.farofa.gm.dao.SchoolDataDAO;
import br.com.farofa.gm.dao.StudentDAO;
import br.com.farofa.gm.dao.TeacherDAO;
import br.com.farofa.gm.model.Room;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.SchoolData;
import br.com.farofa.gm.model.Student;
import br.com.farofa.gm.model.Teacher;
import br.com.farofa.gm.util.DateConverterUtil;
import br.com.farofa.gm.util.SyncCodeGeneratorUtil;

@Named
@RequestScoped
public class GeralImportBean {
	@Inject
	private SchoolDataDAO schoolDataDAO;
	@Inject
	private SchoolDAO schoolDAO;
	@Inject
	private TeacherDAO teacherDAO;
	@Inject
	private RoomDAO roomDAO;
	@Inject
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
    }
    
    private void proccessExcel(UploadedFile uploadedFile) throws Exception{
    	//Declaration of lines as objects
    	Map<String, SchoolData> schoolDataMap = new HashMap<String, SchoolData>();
    	Map<String, School> schoolMap = new HashMap<String, School>();
    	Map<String, Teacher> teacherMap = new HashMap<String, Teacher>();
    	Map<String, Room> roomMap = new HashMap<String, Room>();
    	Map<String, Student> studentMap = new HashMap<String, Student>();
    	//List<Student> studentList = new ArrayList<Student>();
		
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

			if(!rowIterator.hasNext()){
				continue;
			}
						
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
			
			boolean flagError = false;
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
					
					List<SchoolData> sdList = schoolDataDAO.findByInep(codigoInepDaEscola);
					if (sdList == null || sdList.size() == 0) {
						System.out.println("Inep = " + codigoInepDaEscola);
						System.out.println("sdList == null: " + (sdList == null) + " sdList.size() == 0: " + (sdList.size() == 0));
						System.out.println("Inep não cadastrado no banco de dados!");
						flagError = true;
					}
					
					/*try {
						nomeDaEscola = getSchoolNameByInep(codigoInepDaEscola);
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}*/
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
			
			if (flagError) {
				break;
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
			if("Menina".equals(sexo))
				sexoChar = 'F';
			
			//Validate data nascimento
			Date date = DateConverterUtil.stringToDate(dataDeNascimento);
			
			//Settando Keys
			String sdKey = codigoInepDaEscola + "-" + nomeDaEscola;
			String schoolKey = sdKey;
			String teacherKey = professor + "-" + emailProfessor + "-" + codigoInepDaEscola;
			String roomKey = nomeDaTurma + "-" + serie + "-" + periodoChar + "-" + codigoInepDaEscola;
			String studentKey = nomeCompletoDoAluno + "-" + date + "-" + nomeDaTurma + "-" + serie + "-" + periodoChar + "-" + codigoInepDaEscola; 
			
			//Declara objetos
			SchoolData sd = null;
			School school = null;
			Teacher teacher = null;
			Room room = null;
			Student student = null;
			
			//SchoolDataMap
			if(!schoolDataMap.containsKey(sdKey)){
				sd = new SchoolData(codigoInepDaEscola, nomeDaEscola);
				schoolDataMap.put(sdKey, sd);
			}else{
				sd = schoolDataMap.get(sdKey);
			}
			//SchoolMap
			if(!schoolMap.containsKey(schoolKey)){
				school = new School(codigoInepDaEscola, null, null, null, sd);
				schoolMap.put(schoolKey, school);
			}else{
				school = schoolMap.get(schoolKey);
			}
			//TeacherMap
			if(!teacherMap.containsKey(teacherKey)){
				teacher = new Teacher(false, professor, null, null, emailProfessor, null, school);
				teacherMap.put(teacherKey, teacher);
			}else{
				teacher = teacherMap.get(teacherKey);
			}
			//RoomMap
			if(!roomMap.containsKey(roomKey)){
				room = new Room(false, nomeDaTurma, serie, periodoChar, teacher, null);
				roomMap.put(roomKey, room);
			}else{
				room = roomMap.get(roomKey);
			}
			//StudentMap
			if(!studentMap.containsKey(studentKey)){
				student = new Student(false, nomeCompletoDoAluno, sexoChar, date, "NOT_ENOUGH_INPUT", null, null, null, null, null, room);
				studentMap.put(studentKey, student);
			}else{
				student = studentMap.get(studentKey);
			}
		}		
		
		//Start Updating the DataBase
		
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
		
		//Room
		for (Room room : roomMap.values()){
			Room tmp = roomDAO.findByNameAndSerieAndPeriodAndInep(room.getName(), room.getSerie(), room.getTerm(), 
					room.getTeacher().getSchool().getSchoolData().getInep());
			if(tmp != null){
				room.setId(tmp.getId());
			}else{
				roomDAO.save(room);
			}
		}
		
		//Student
		for (Student student : studentMap.values()){
			Student tmp = studentDAO.findByNameDateRoomInep(student.getName(), student.getBirth_date(), 
					student.getRoom(), student.getRoom().getTeacher().getSchool().getSchoolData().getInep());
			if(tmp != null)
				student.setId(tmp.getId());
			else
				studentDAO.save(student);
		}
	}
    
	@SuppressWarnings("unused")
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
			e.printStackTrace();
			throw e;
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