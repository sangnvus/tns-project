package vn.co.taxinet.mobile.model;

import java.io.Serializable;

public class Driver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8026349913914275141L;
	private String id;
	private String firstName;
	private String lastName;
	private String image;
	private String email;
	private String phoneNumber;
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Driver(String id, String firstName, String lastName, String image,
			String email, String phoneNumber, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.image = image;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	public Driver() {
		super();
	}

}
