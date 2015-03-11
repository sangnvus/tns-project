package vn.co.taxinet.mobile.model;

public class Trip {
	private String id;
	private String status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Trip(String id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	public Trip() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
