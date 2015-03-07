package vn.co.taxinet.mobile.model;

public class Rider {
	private String id;
	private String fullname;
	private String image;
	private String email;
	private String phone;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Rider(String id, String fullname, String image, String email,
			String phone) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.image = image;
		this.email = email;
		this.phone = phone;
	}
	public Rider() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
