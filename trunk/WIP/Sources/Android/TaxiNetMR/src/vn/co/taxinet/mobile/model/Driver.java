package vn.co.taxinet.mobile.model;

import java.io.Serializable;

public class Driver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8026349913914275141L;
	private int id;
	private String name;
	private String phone_number;
	private boolean status;

	public Driver() {
		super();
	}

	public Driver(int id, String name, String phone_number,
			boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.phone_number = phone_number;
		this.status = status;
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

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
