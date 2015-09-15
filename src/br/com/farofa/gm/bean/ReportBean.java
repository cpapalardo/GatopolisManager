package br.com.farofa.gm.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.farofa.gatopolisws.dao.ReportStudentDAO;
import br.com.farofa.gatopolisws.dao.ReportTeacherDAO;
import br.com.farofa.gatopolisws.dao.SchoolDataDAO;
import br.com.farofa.gatopolisws.model.SchoolData;
import br.com.farofa.gm.bean.item.ReportStudentItem;
import br.com.farofa.gm.bean.item.ReportTeacherItem;

@Named
@RequestScoped
public class ReportBean {
	@Inject
	private SchoolDataDAO schoolDataDAO;
	@Inject
	private ReportTeacherDAO reportTeacherDAO;
	@Inject
	private ReportStudentDAO reportStudentDAO;
	
	private int schoolId;
	private List <SelectItem> schoolItens;
	
	private List <ReportTeacherItem> rtiList;
	private List <ReportStudentItem> rsiList;

	@PostConstruct
	public void init () {
		schoolItens = new ArrayList<SelectItem> ();
		for (SchoolData schoolData : schoolDataDAO.findAll()) {
			schoolItens.add (new SelectItem (schoolData.getInep(), schoolData.getName()));
		}
	}
	
	public void showReport () {
		/*rtiList = reportTeacherDAO.findReportTeacherItensBySchool(schoolId);
		rsiList = reportStudentDAO.findReportStudentItensBySchool(schoolId);*/
	}
	
	public String back(){
		return "home";
	}
	
	
	//Getters and Setters
	public List<ReportTeacherItem> getRtiList() {
		return rtiList;
	}

	public void setRtiList(List<ReportTeacherItem> rtiList) {
		this.rtiList = rtiList;
	}

	public List<ReportStudentItem> getRsiList() {
		return rsiList;
	}

	public void setRsiList(List<ReportStudentItem> rsiList) {
		this.rsiList = rsiList;
	}
	
	public List<SelectItem> getSchoolItens() {
		return schoolItens;
	}

	public void setSchoolItens(List<SelectItem> schoolItens) {
		this.schoolItens = schoolItens;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

}