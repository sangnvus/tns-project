package hieugie.capstone.taxinet.model;

public class TaxiCompanyItem {
	private String name;
	private String phone_number;

	public TaxiCompanyItem() {
		super();
	}

	public TaxiCompanyItem(String name, String phone_number) {
		super();
		this.name = name;
		this.phone_number = phone_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

}
