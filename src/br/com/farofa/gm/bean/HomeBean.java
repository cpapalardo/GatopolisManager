package br.com.farofa.gm.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.farofa.gm.dao.SchoolDAOImpl;
import br.com.farofa.gm.model.School;

@Named
@RequestScoped
public class HomeBean {
	@Inject
	private SchoolDAOImpl schoolDAO;
	
	private String enviroment;
	
	private String syncCode;
	private String password;
	private String access;
	
	@PostConstruct
	public void init () {
		access = "farofa2015";
	}
	
	public String access () {
		if (syncCode != null && !syncCode.equals("")) {
			School school = schoolDAO.findBySyncCode (syncCode);
			
			if (school != null) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("schoolName", school.getSchoolData().getName());  
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("schoolSyncCode", school.getSync_code());  
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("school", school);
				return "teachers";
			} else {
		        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Codigo de Sincronizacao nao encontrado.", null));
		        return "home";
			}
		} else {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "O campo Codigo de Sincronizacao deve ser preenchido.", null));
			return "home";
		}
	}
	
	public String entering () {
		if (password != null && password.equals(access)) {
			System.out.println(password);
			return "report";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Password incorrect."));
			return "home";
		}
	}

	public String getSyncCode() {
		return syncCode;
	}

	public void setSyncCode(String syncCode) {
		this.syncCode = syncCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getEnviroment() {
		return enviroment;
	}

	public void setEnviroment(String enviroment) {
		this.enviroment = enviroment;
	}
	
}
