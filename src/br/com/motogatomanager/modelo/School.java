package br.com.motogatomanager.modelo;

public class School extends Generic {
	private String name;
	private String sync_code;

	public School() {}

	public School(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSync_code() {
		return sync_code;
	}

	public void setSync_code(String sync_code) {
		this.sync_code = sync_code;
	}
	
}
