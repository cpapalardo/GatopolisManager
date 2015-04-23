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
	private String password;
	private String access;
	
	@PostConstruct
	public void init () {
		access = "farofa2015";
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
		        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Codigo de Sincronizacao nao encontrado."));
		        return "home";
			}
		} else {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "O campo Codigo de Sincronizacao deve ser preenchido."));
			return "home";
		}
	}
	
	public String entering () {
		if (password != null && password.equals(access)) {
			System.out.println(password);
			return "Manager/report";
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
	
}
