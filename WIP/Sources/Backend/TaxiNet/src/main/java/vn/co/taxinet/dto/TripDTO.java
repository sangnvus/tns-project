package vn.co.taxinet.dto;

public class TripDTO {
	private String id;
	private String message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TripDTO(String id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	public TripDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
