package br.com.motogatomanager.modelo;

public class Generic {
	private int id;
	
	public Generic () {}
	
	public Generic(int id, String createdAt, String updatedAt) {
		this.id = id;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Generic [id=" + id + "]";
	}
	
	
}
