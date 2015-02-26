package br.com.motogatomanager.bean;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
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
        	ExcelUtil excel = new ExcelUtil();
        	
        	File file = new File(uploadedFile.getFileName());
        	
        	try {
        		FileUtils.copyInputStreamToFile(uploadedFile.getInputstream(), file);
				excel.read (file);
				
				/*//Removendo redundancia
				int aux = 1;
				for (int i = 0; i < excel.getGroups().size(); i++) {
					if (excel.getGroups().get(i).getId() != aux) 
						excel.getGroups().remove(i);
					else
						aux++;
				}
				
				//Realoca as Classes na lista de Alunos após salva-las no banco
				for (StudentGroup sg : excel.getGroups()) {
					int tmp = sg.getId();
					sg.setSchool(school);
					new StudentGroupDAO ().save(sg);
					
					//Realocando
					for (Student s : excel.getStudents()) {
						if (s.getStudent_group().getId() == tmp) {
							s.setStudent_group(sg);
						}
					}
				}*/
				
				for (Student s : excel.getStudents()) {
					//TODO Alterar isso
					StudentGroup sg = new StudentGroup();
					sg.setId(1);
					s.setStudent_group(sg);
					s.setSchool(school);
					new StudentDAO ().save (s);
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
        	FacesMessage message = new FacesMessage("Erro!", "O Upload não foi enviado para o servidor!");
        	FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
	
}
