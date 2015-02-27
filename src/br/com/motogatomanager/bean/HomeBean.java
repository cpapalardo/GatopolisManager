package br.com.motogatomanager.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.motogatomanager.dao.SchoolDAO;
import br.com.motogatomanager.modelo.School;

@ManagedBean
public class HomeBean {
	private String syncCode;
	
	@PostConstruct
	public void init () {
		
	}
	
	public String access () {
		if (syncCode != null && !syncCode.equals("")) {
			//School school = BancoLocal.SCHOOLS.get(0);
			School school = new SchoolDAO ().fetchBySyncCode (syncCode);
			
			if (school.getSync_code() != null && school.getSync_code().equals(syncCode)) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("schoolName", school.getName());  
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("schoolSyncCode", school.getSync_code());  
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("school", school);  
				return "Manager/teachers";
			} else {
		        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Código de Sincronização não encontrado."));
		        return "home";
			}
		} else {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "O campo Código de Sincronização deve ser preenchido."));
			return "home";
		}
	}

	public String getSyncCode() {
		return syncCode;
	}

	public void setSyncCode(String syncCode) {
		this.syncCode = syncCode;
	}
	
	/*public String listarEscolas() {
		return "Manager/schools";
	}

	public String listarProfessores() {
		return "Manager/teachers";
	}

	public String listarTurmas() {
		return "Manager/groups";
	}

	public String listarAlunos() {
		return "Manager/students";
	}

	public String importar() {
		return "Manager/import";
	}*/
	
	
}
