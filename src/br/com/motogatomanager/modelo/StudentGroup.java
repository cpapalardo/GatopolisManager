package br.com.motogatomanager.modelo;

import java.util.Date;

public class StudentGroup extends Generic {
	private String name;
	private String series;
	private String period;
	private Date deletedAt;
	private School school;
	
	public StudentGroup () {}
	
	public StudentGroup(String name, String series, String period, Date deletedAt,
			School school) {
		this.name = name;
		this.series = series;
		this.period = period;
		this.deletedAt = deletedAt;
		this.school = school;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}

	@Override
	public String toString() {
		return "StudentGroup [name=" + name + ", series=" + series
				+ ", period=" + period + ", deletedAt=" + deletedAt
				+ ", school=" + school + ", getId()=" + getId()
				+ ", getCreatedAt()=" + getCreatedAt() + ", getUpdatedAt()="
				+ getUpdatedAt() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
