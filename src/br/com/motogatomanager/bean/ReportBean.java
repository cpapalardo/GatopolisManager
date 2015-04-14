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
import br.com.motogatomanager.modelo.ReportStudent;
import br.com.motogatomanager.modelo.ReportTeacher;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.ViewedStudent;

@ManagedBean
public class ReportBean {
	private School schoolSelected;
	private List <SelectItem> schoolItens;
	
	private List <ViewedStudent> viewedStudents;
	private List <ReportTeacher> rtList;
	private List <ReportStudent> rsList;
	
	private List <ReportTeacherItem> rtiList;
	private List <ReportStudentItem> rsiList;

	@PostConstruct
	public void init () {
		schoolItens = new ArrayList<SelectItem> (); 
		for (School school : new SchoolDAO ().fetchAll()) {
			schoolItens.add(new SelectItem (school, school.getName ()));
		}
	}
	
	public void showReport () {
		rtiList = new ReportTeacherDAO().getReportTeacherItensBySchool(schoolSelected);
		rsiList = new ReportStudentDAO().getReportStudentItensBySchool(schoolSelected);
	}

	public List<ViewedStudent> getViewedStudents() {
		return viewedStudents;
	}

	public void setViewedStudents(List<ViewedStudent> viewedStudents) {
		this.viewedStudents = viewedStudents;
	}

	public List<ReportTeacher> getRtList() {
		return rtList;
	}

	public void setRtList(List<ReportTeacher> rtList) {
		this.rtList = rtList;
	}

	public List<ReportStudent> getRsList() {
		return rsList;
	}

	public void setRsList(List<ReportStudent> rsList) {
		this.rsList = rsList;
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

	public School getSchoolSelected() {
		return schoolSelected;
	}

	public void setSchoolSelected(School schoolSelected) {
		this.schoolSelected = schoolSelected;
	}

	public List<SelectItem> getSchoolItens() {
		return schoolItens;
	}

	public void setSchoolItens(List<SelectItem> schoolItens) {
		this.schoolItens = schoolItens;
	}

}