package br.com.farofa.gm.bean;

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
import br.com.farofa.gm.dao.StudentDAO;
import br.com.farofa.gm.model.Room;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.Student;
import br.com.farofa.gm.model.Teacher;
import br.com.farofa.gm.util.DateConverterUtil;

@Named
@RequestScoped
public class ImportBean {
	@Inject
	private RoomDAO roomDAO;
	@Inject
	private StudentDAO studentDAO;
	
	private School school;
	private Teacher teacher;
	
	private UploadedFile uploadedFile;
	
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
    
    public void upload() {
        if(uploadedFile != null){
        	try{
        		proccessExcel(uploadedFile);
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso!", "O arquivo " + uploadedFile.getFileName() + " foi adicionado com sucesso."));
        	}catch(Exception e) {
				e.printStackTrace();
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro!", "Erro no envio do Excel!"));
			}
        }else{
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro!", "Não foi possível concluir o envio do arquivo!"));
        }
    }
    
	private void proccessExcel(UploadedFile uploadedFile) throws Exception{
    	//Declaration of lines as objects
    	Map<String,Room> roomMap = new HashMap<String,Room>();
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
			
			String nomeDoAluno = null;
			String sobrenomeDoAluno = null;
			String dataDeNascimento = null;
			String sexo = null;
			String nomeDaTurma = null;
			String periodo = null;
			String serie = null;
			
			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				int columnIndex = cell.getColumnIndex();
				
				//Nome do Aluno
				if(columnIndex == 0){
					nomeDoAluno = cell.getStringCellValue();
				}
				
				//Sobrenome do Aluno
				if(columnIndex == 1){
					sobrenomeDoAluno = cell.getStringCellValue();
				}
				//Data de Nascimento
				else if(columnIndex == 2){
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
				else if(columnIndex == 3){
					sexo = cell.getStringCellValue();
				}
				//Nome da Turma
				else if(columnIndex == 4){
					nomeDaTurma = cell.getStringCellValue();
				}
				//Periodo
				else if(columnIndex == 5){
					periodo = cell.getStringCellValue();
				}
				//Serie
				else if(columnIndex == 6){
					serie = cell.getStringCellValue();
				}
			}
			
			System.out.print(nomeDaTurma + "\t");
			System.out.print(serie + "\t");
			System.out.print(periodo + "\t");
			System.out.print(nomeDoAluno + "\t");
			System.out.print(sobrenomeDoAluno + "\t");
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
			String roomKey = nomeDaTurma + "-" + serie + "-" + periodoChar + "-" + school.getSchoolData().getInep();
			
			//Declara Objetos
			Room room = null;
			
			//RoomMap
			if(!roomMap.containsKey(roomKey)){
				room = new Room(false, nomeDaTurma, serie, periodoChar, teacher, null);
				roomMap.put(roomKey, room);
			}else{
				room = roomMap.get(roomKey);
			}
			
			//StudentList
			Student student = new Student(false, nomeDoAluno, sobrenomeDoAluno, sexoChar, date, "NOT_ENOUGH_INPUT", null, null, null, null, null, room, "");
			studentList.add(student);
		}
		
		
		//Start Updating de DataBase
		
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
		for (Student student : studentList){
			//student.setDiagnosis_level("NOT_ENOUGH_INPUT");
			studentDAO.save(student);
		}
    }
	
    //Getters and Setters
    
    public UploadedFile getFile() {
        return uploadedFile;
    }
 
    public void setFile(UploadedFile file) {
        this.uploadedFile = file;
    }
}
