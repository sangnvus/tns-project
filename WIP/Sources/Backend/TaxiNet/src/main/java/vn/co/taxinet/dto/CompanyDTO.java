package vn.co.taxinet.dto;

public class CompanyDTO {
	private String name;
	private String address;
	private String city;
	private String postalCode;
	private String phone;
	private String vatNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public CompanyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanyDTO(String name, String address, String city,
			String postalCode, String phone, String vatNumber) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
		this.phone = phone;
		this.vatNumber = vatNumber;
	}

}
