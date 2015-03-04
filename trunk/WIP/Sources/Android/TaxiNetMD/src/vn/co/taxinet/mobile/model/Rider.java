package vn.co.taxinet.mobile.model;

public class Rider {
	private int id;
	private String name;
	private String image;
	private double longitude;
	private double latitude;
	private String phone;

	public Rider(int id, String name, String image, double longitude,
			double latitude, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.longitude = longitude;
		this.latitude = latitude;
		this.phone = phone;
	}

	public Rider() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
