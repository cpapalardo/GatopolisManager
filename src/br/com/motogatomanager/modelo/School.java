package br.com.motogatomanager.modelo;

public class School extends Generic {
	private String coordinator_code;
	private String name;
	private String public_id;
	private String sync_code;

	public School() {}

	public School(String coordinator_code, String name, String public_id, String sync_code) {
		this.coordinator_code = coordinator_code;
		this.name = name;
		this.public_id = public_id;
		this.sync_code = sync_code;
	}



	public String getCoordinator_code() {
		return coordinator_code;
	}

	public void setCoordinator_code(String coordinator_code) {
		this.coordinator_code = coordinator_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublic_id() {
		return public_id;
	}

	public void setPublic_id(String public_id) {
		this.public_id = public_id;
	}

	public String getSync_code() {
		return sync_code;
	}

	public void setSync_code(String sync_code) {
		this.sync_code = sync_code;
	}

	@Override
	public String toString() {
		return "School [coordinator_code=" + coordinator_code + ", name="
				+ name + ", public_id=" + public_id + ", sync_code="
				+ sync_code + ", getObjectId()=" + getObjectId()
				+ ", getCreatedAt()=" + getCreatedAt() + ", getUpdatedAt()="
				+ getUpdatedAt() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
