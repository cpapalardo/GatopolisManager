package br.com.motogatomanager.modelo;

public class Generic {
	private String objectId;
	private String createdAt;
	private String updatedAt;
	
	public Generic () {}
	
	public Generic(String objectId, String createdAt, String updatedAt) {
		this.objectId = objectId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
}
