package br.com.motogatomanager.modelo;

import java.util.Date;

public class ReportStudent extends Generic {
	private Date date_access;
	private Student student;
	private School school;
	
	public ReportStudent() {
		super();
	}
	
	public ReportStudent(Date date_access, Student student, School school) {
		super();
		this.date_access = date_access;
		this.student = student;
		this.school = school;
	}
	
	public Date getDate_access() {
		return date_access;
	}
	public void setDate_access(Date date_access) {
		this.date_access = date_access;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	@Override
	public String toString() {
		return super.toString() + "ReportStudent [date_access=" + date_access + ", student="
				+ student + ", school=" + school + "]";
	}
	
}
