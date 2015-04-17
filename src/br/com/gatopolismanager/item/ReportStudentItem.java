package br.com.gatopolismanager.item;

public class ReportStudentItem {
	private String teacherName;
	private String studentName;
	private int accessedThisWeek;
	private int accessedThisMonth;
	private int accessedThisYear;
	private int viewedCount;
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getAccessedThisWeek() {
		return accessedThisWeek;
	}
	public void setAccessedThisWeek(int accessedThisWeek) {
		this.accessedThisWeek = accessedThisWeek;
	}
	public int getAccessedThisMonth() {
		return accessedThisMonth;
	}
	public void setAccessedThisMonth(int accessedThisMonth) {
		this.accessedThisMonth = accessedThisMonth;
	}
	public int getAccessedThisYear() {
		return accessedThisYear;
	}
	public void setAccessedThisYear(int accessedThisYear) {
		this.accessedThisYear = accessedThisYear;
	}
	public int getViewedCount() {
		return viewedCount;
	}
	public void setViewedCount(int viewedCount) {
		this.viewedCount = viewedCount;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
}
