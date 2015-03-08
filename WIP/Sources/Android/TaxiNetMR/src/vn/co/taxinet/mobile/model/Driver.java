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
	private Double longitude;
	private Double latitude;
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
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Driver(String id, String firstName, String lastName, String image,
			Double longitude, Double latitude) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.image = image;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
