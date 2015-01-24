package vn.co.taxinet.orm;

// Generated Jan 23, 2015 10:11:00 AM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Carmodel generated by hbm2java
 */
public class Carmodel implements java.io.Serializable {

	private int carModelId;
	private Carmaker carmaker;
	private String carModel;
	private String carType;
	private int capacity;
	private String createdBy;
	private Date creatDate;
	private String lastModifiedBy;
	private Date lastModifyDate;
	private Set<Pricepanel> pricepanels = new HashSet<Pricepanel>(0);
	private Set<Pricepanel> pricepanels_1 = new HashSet<Pricepanel>(0);

	public Carmodel() {
	}

	public Carmodel(Carmaker carmaker, String carModel, String carType,
			int capacity, String createdBy, Date creatDate,
			String lastModifiedBy, Date lastModifyDate) {
		this.carmaker = carmaker;
		this.carModel = carModel;
		this.carType = carType;
		this.capacity = capacity;
		this.createdBy = createdBy;
		this.creatDate = creatDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifyDate = lastModifyDate;
	}

	public Carmodel(Carmaker carmaker, String carModel, String carType,
			int capacity, String createdBy, Date creatDate,
			String lastModifiedBy, Date lastModifyDate,
			Set<Pricepanel> pricepanels, Set<Pricepanel> pricepanels_1) {
		this.carmaker = carmaker;
		this.carModel = carModel;
		this.carType = carType;
		this.capacity = capacity;
		this.createdBy = createdBy;
		this.creatDate = creatDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifyDate = lastModifyDate;
		this.pricepanels = pricepanels;
		this.pricepanels_1 = pricepanels_1;
	}

	public int getCarModelId() {
		return this.carModelId;
	}

	public void setCarModelId(int carModelId) {
		this.carModelId = carModelId;
	}

	public Carmaker getCarmaker() {
		return this.carmaker;
	}

	public void setCarmaker(Carmaker carmaker) {
		this.carmaker = carmaker;
	}

	public String getCarModel() {
		return this.carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarType() {
		return this.carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatDate() {
		return this.creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifyDate() {
		return this.lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public Set<Pricepanel> getPricepanels() {
		return this.pricepanels;
	}

	public void setPricepanels(Set<Pricepanel> pricepanels) {
		this.pricepanels = pricepanels;
	}

	public Set<Pricepanel> getPricepanels_1() {
		return this.pricepanels_1;
	}

	public void setPricepanels_1(Set<Pricepanel> pricepanels_1) {
		this.pricepanels_1 = pricepanels_1;
	}

}
