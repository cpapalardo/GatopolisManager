package br.com.motogatomanager.bean;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.UploadedFile;

import br.com.motogatomanager.db.StaticDB;
import br.com.motogatomanager.modelo.Group;
import br.com.motogatomanager.modelo.Student;
import br.com.motogatomanager.modelo.Teacher;
import br.com.motogatomanager.util.ExcelUtil;

@ManagedBean
public class ImportBean {
	
	private Teacher teacher;
	
	@PostConstruct
	public void init() {
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
				
				StaticDB.STUDENTS.addAll(excel.getStudents());
				StaticDB.GROUPS.addAll(excel.getGroups());
				
				for (Student s : excel.getStudents())
					System.out.println(s);
				
				for (Group g : excel.getGroups())
					System.out.println(g);
				
				FacesMessage message = new FacesMessage("Sucesso!", uploadedFile.getFileName() + " foi adicionado.");
				FacesContext.getCurrentInstance().addMessage(null, message);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
	
}
