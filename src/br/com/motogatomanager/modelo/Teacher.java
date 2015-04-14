package br.com.motogatomanager.modelo;

public class Teacher extends Generic {
	private String name;
	private String last_name;
	private String passcode;
	private String email;
	private String question;
	private String answer;
	private School school;
	private String picture;
	
	public Teacher () {}
	
	public Teacher(String name, String last_name, String passcode,
			String email, String question,
			String answer, School school) {
		this.name = name;
		this.last_name = last_name;
		this.passcode = passcode;
		this.email = email;
		this.question = question;
		this.answer = answer;
		this.school = school;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return super.toString() + "Teacher [name=" + name + ", last_name=" + last_name
				+ ", passcode=" + passcode + ", email=" + email + ", question="
				+ question + ", answer=" + answer + ", school=" + school
				+ ", picture=" + picture + "]";
	}

	
}
