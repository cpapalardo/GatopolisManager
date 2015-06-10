package br.com.farofa.gm.bean;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.farofa.gm.dao.SchoolDAO;
import br.com.farofa.gm.dao.SchoolDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.manager.Enviroment;
import br.com.farofa.gm.model.School;

@ManagedBean
public class HomeBean {
	private SchoolDAO schoolDAO;
	
	private String enviroment;
	
	private String syncCode;
	private String password;
	private String access;
	
	@PostConstruct
	public void init () {
		enviroment = DataBaseManager.getEnviroment();
		if(enviroment == Enviroment.gatopolis_v2_db.name())
			enviroment = "Ambiente de Produção";
		access = "farofa2015";
		

		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			String hostname = addr.getHostName();
			enviroment = hostname;
			if (enviroment.equals("RD00155D003742")) {
				enviroment = "O nome dessa máquina é " + hostname;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public String access () {
		schoolDAO = new SchoolDAOImpl(DataBaseManager.getEntityManager());
		if (syncCode != null && !syncCode.equals("")) {
			School school = schoolDAO.findBySyncCode (syncCode);
			DataBaseManager.close();
			
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
