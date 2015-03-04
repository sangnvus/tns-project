package vn.co.taxinet.orm;

// Generated Jan 28, 2015 9:41:06 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CarModel generated by hbm2java
 */
public class CarModel implements java.io.Serializable {

	private Integer carModelId;
	private CarMaker carMaker;
	private String carModel;
	private String carType;
	private int capacity;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;
	private Set<PricePanel> pricePanels = new HashSet<PricePanel>(0);
	private Set<PricePanel> pricepanels_1 = new HashSet<PricePanel>(0);

	public CarModel() {
	}

	public CarModel(CarMaker carMaker, String carModel, String carType,
			int capacity, String createdBy, Date createdDate,
			String lastModifiedBy, Date lastModifiedDate) {
		this.carMaker = carMaker;
		this.carModel = carModel;
		this.carType = carType;
		this.capacity = capacity;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public CarModel(CarMaker carMaker, String carModel, String carType,
			int capacity, String createdBy, Date createdDate,
			String lastModifiedBy, Date lastModifiedDate,
			Set<PricePanel> pricePanels, Set<PricePanel> pricepanels_1) {
		this.carMaker = carMaker;
		this.carModel = carModel;
		this.carType = carType;
		this.capacity = capacity;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
		this.pricePanels = pricePanels;
		this.pricepanels_1 = pricepanels_1;
	}

	public Integer getCarModelId() {
		return this.carModelId;
	}

	public void setCarModelId(Integer carModelId) {
		this.carModelId = carModelId;
	}

	public CarMaker getCarmaker() {
		return this.carMaker;
	}

	public void setCarmaker(CarMaker carMaker) {
		this.carMaker = carMaker;
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

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Set<PricePanel> getPricepanels() {
		return this.pricePanels;
	}

	public void setPricepanels(Set<PricePanel> pricePanels) {
		this.pricePanels = pricePanels;
	}

	public Set<PricePanel> getPricepanels_1() {
		return this.pricepanels_1;
	}

	public void setPricepanels_1(Set<PricePanel> pricepanels_1) {
		this.pricepanels_1 = pricepanels_1;
	}

}