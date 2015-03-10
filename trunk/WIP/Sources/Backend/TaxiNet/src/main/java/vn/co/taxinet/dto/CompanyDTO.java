package vn.co.taxinet.dto;

public class CompanyDTO {
	private String name;
	private String address;
	private String city;
	private String zip_code;
	private String phone;
	private String vat_number;

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

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVat_number() {
		return vat_number;
	}

	public void setVat_number(String vat_number) {
		this.vat_number = vat_number;
	}

	public CompanyDTO(String name, String address, String city,
			String zip_code, String phone, String vat_number) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.zip_code = zip_code;
		this.phone = phone;
		this.vat_number = vat_number;
	}

	public CompanyDTO() {
		super();
	}

}
