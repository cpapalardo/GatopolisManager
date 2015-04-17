package br.com.motogatomanager.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import br.com.gatopolismanager.item.ReportStudentItem;
import br.com.gatopolismanager.item.ReportTeacherItem;
import br.com.motogatomanager.dao.ReportStudentDAO;
import br.com.motogatomanager.dao.ReportTeacherDAO;
import br.com.motogatomanager.dao.SchoolDAO;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.ViewedStudent;

@ManagedBean
public class ReportBean {
	public int schoolId;
	private List <SelectItem> schoolItens;
	
	private List <ViewedStudent> viewedStudents;
	
	private List <ReportTeacherItem> rtiList;
	private List <ReportStudentItem> rsiList;

	@PostConstruct
	public void init () {
		schoolItens = new ArrayList<SelectItem> ();
		for (School school : new SchoolDAO ().fetchAll())
			schoolItens.add (new SelectItem (school.getId(), school.getName()));
	}
	
	public void showReport () {
		rtiList = new ReportTeacherDAO().getReportTeacherItensBySchool(schoolId);
		rsiList = new ReportStudentDAO().getReportStudentItensBySchool(schoolId);
	}

	public List<ViewedStudent> getViewedStudents() {
		return viewedStudents;
	}

	public void setViewedStudents(List<ViewedStudent> viewedStudents) {
		this.viewedStudents = viewedStudents;
	}

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