package br.com.motogatomanager.modelo;

import java.util.Arrays;

public class Teacher extends Generic {
	private String name;
	private String last_name;
	private String passcode;
	private String email;
	private boolean is_coordinator;
	private String question;
	private String answer;
	private School school;
	private byte[] picture;
	
	public Teacher () {}
	
	public Teacher(String name, String last_name, String passcode,
			String email, boolean is_coordinator, String question,
			String answer, School school, byte[] picture) {
		this.name = name;
		this.last_name = last_name;
		this.passcode = passcode;
		this.email = email;
		this.is_coordinator = is_coordinator;
		this.question = question;
		this.answer = answer;
		this.school = school;
		this.picture = picture;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isIs_coordinator() {
		return is_coordinator;
	}
	public void setIs_coordinator(boolean is_coordinator) {
		this.is_coordinator = is_coordinator;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasscode() {
		return passcode;
	}
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Teacher [name=" + name + ", last_name=" + last_name
				+ ", passcode=" + passcode + ", email=" + email
				+ ", is_coordinator=" + is_coordinator + ", question="
				+ question + ", answer=" + answer + ", school=" + school
				+ ", picture=" + Arrays.toString(picture) + ", getObjectId()="
				+ getObjectId() + ", getCreatedAt()=" + getCreatedAt()
				+ ", getUpdatedAt()=" + getUpdatedAt() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}
