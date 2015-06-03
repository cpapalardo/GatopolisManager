package br.com.farofa.gm.bean;

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

import br.com.farofa.gm.dao.RoomDAO;
import br.com.farofa.gm.dao.RoomDAOImpl;
import br.com.farofa.gm.dao.StudentDAO;
import br.com.farofa.gm.dao.StudentDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.Room;
import br.com.farofa.gm.model.School;
import br.com.farofa.gm.model.Student;
import br.com.farofa.gm.model.Teacher;
import br.com.farofa.gm.util.DateConverterUtil;

@ManagedBean
public class ImportBean {
	private RoomDAO groupDAO;
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
    	groupDAO = new RoomDAOImpl(DataBaseManager.getEntityManager());
    	studentDAO = new StudentDAOImpl(DataBaseManager.getEntityManager());
    	
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
        
        DataBaseManager.close();
    }
    
	private void proccessExcel(UploadedFile uploadedFile) throws Exception{
    	//Declaration of lines as objects
    	Map<String,Room> groupMap = new HashMap<String,Room>();
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
			
			String nomeCompletoDoAluno = null;
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
				
				//Nome Completo do Aluno
				if(columnIndex == 0){
					nomeCompletoDoAluno = cell.getStringCellValue();
				}
				//Data de Nascimento
				else if(columnIndex == 1){
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
				else if(columnIndex == 2){
					sexo = cell.getStringCellValue();
				}
				//Nome da Turma
				else if(columnIndex == 3){
					nomeDaTurma = cell.getStringCellValue();
				}
				//Periodo
				else if(columnIndex == 4){
					periodo = cell.getStringCellValue();
				}
				//Serie
				else if(columnIndex == 5){
					serie = cell.getStringCellValue();
				}
			}
			
			System.out.print(nomeDaTurma + "\t");
			System.out.print(serie + "\t");
			System.out.print(periodo + "\t");
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
			String groupKey = nomeDaTurma + "-" + serie + "-" + periodoChar + "-" + school.getSchoolData().getInep();
			
			//Declara Objetos
			Room group = null;
			
			//GroupMap
			if(!groupMap.containsKey(groupKey)){
				group = new Room(null, nomeDaTurma, serie, periodoChar, teacher, null);
				groupMap.put(groupKey, group);
			}else{
				group = groupMap.get(groupKey);
			}
			
			//StudentList
			Student student = new Student(null, nomeCompletoDoAluno, sexoChar, date, null, null, null, null, null, group);
			studentList.add(student);
		}
		
		
		//Start Updating de DataBase
		
		//Group
		for (Room group : groupMap.values()){
			Room tmp = groupDAO.findByNameAndSerieAndPeriodAndInep(group.getName(), group.getSerie(), group.getTerm(), 
					group.getTeacher().getSchool().getSchoolData().getInep());
			if(tmp != null){
				group.setId(tmp.getId());
			}else{
				groupDAO.save(group);
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
