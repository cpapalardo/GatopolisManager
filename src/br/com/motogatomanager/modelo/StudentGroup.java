package br.com.motogatomanager.modelo;


public class StudentGroup extends Generic {
	private String name;
	private String series;
	private String period;
	private School school;
	
	//This is not in DB
	private String professores;
	private int qtdeAlunos;
	
	public StudentGroup () {}
	
	public StudentGroup(String name, String series, String period, School school) {
		this.name = name;
		this.series = series;
		this.period = period;
		this.school = school;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
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

	public int getQtdeAlunos() {
		return qtdeAlunos;
	}

	public void setQtdeAlunos(int qtdeAlunos) {
		this.qtdeAlunos = qtdeAlunos;
	}

	public String getProfessores() {
		return professores;
	}

	public void setProfessores(String professores) {
		this.professores = professores;
	}
	
}
