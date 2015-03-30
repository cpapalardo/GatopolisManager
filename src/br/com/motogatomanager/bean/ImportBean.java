package br.com.motogatomanager.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import br.com.motogatomanager.dao.StudentDAO;
import br.com.motogatomanager.dao.StudentGroupDAO;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.Student;
import br.com.motogatomanager.modelo.StudentGroup;
import br.com.motogatomanager.modelo.Teacher;
import br.com.motogatomanager.util.ExcelUtil;

@ManagedBean
public class ImportBean {
	private School school;
	private Teacher teacher;
	
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
        		ExcelUtil excel = new ExcelUtil();
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
    				student.setDiagnosis_level("NOT_ENOUGH_INPUT");
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
        	FacesMessage message = new FacesMessage("Erro!", "O Upload n�o foi enviado para o servidor!");
        	FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
	
}
