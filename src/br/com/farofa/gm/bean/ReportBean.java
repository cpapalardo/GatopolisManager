package br.com.farofa.gm.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.farofa.gm.bean.item.ReportStudentItem;
import br.com.farofa.gm.bean.item.ReportTeacherItem;
import br.com.farofa.gm.dao.SchoolDAO;
import br.com.farofa.gm.model.School;

@Named
@RequestScoped
public class ReportBean {
	@Inject
	private SchoolDAO schoolDAO;
	
	private int schoolId;
	private List <SelectItem> schoolItens;
	
	private List <ReportTeacherItem> rtiList;
	private List <ReportStudentItem> rsiList;

	@PostConstruct
	public void init () {
		schoolItens = new ArrayList<SelectItem> ();
		for (School school : schoolDAO.findAll())
			schoolItens.add (new SelectItem (school.getSchoolData().getInep(), school.getSchoolData().getName()));
	}
	
	public void showReport () {
		/*rtiList = new ReportTeacherDAOImpl().findReportTeacherItensBySchool(schoolId);
		rsiList = new ReportStudentDAOImpl().findReportStudentItensBySchool(schoolId);*/
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