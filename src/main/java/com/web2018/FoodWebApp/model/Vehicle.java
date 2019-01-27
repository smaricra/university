package com.web2018.FoodWebApp.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.simpleframework.xml.Element;

import com.web2018.FoodWebApp.core.Constants.VEHICLE_TYPE;

public class Vehicle extends Model{
	
	@Element
	private String brand;
	@Element
	private String model;
	@Element
	private VEHICLE_TYPE type;
	@Element
	private String regNumber;
	@Element
	private int productionYear;
	@Element
	private boolean available;
	@Element(required=false)
	private String note;
	@Element(required=false)
	private Long delivererId;
	
	public Vehicle() {
		super();
	}

	public Vehicle(String brand, String model, VEHICLE_TYPE type, String regNumber, int productionYear, boolean available, String note) {
		super();
		this.brand = brand;
		this.model = model;
		this.type = type;
		this.regNumber = regNumber;
		this.productionYear = productionYear;
		this.available = available;
		this.note = note;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public VEHICLE_TYPE getType() {
		return type;
	}

	public void setType(VEHICLE_TYPE type) {
		this.type = type;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public int getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Long getDelivererId() {
		return delivererId;
	}

	public void setDelivererId(Long delivererId) {
		this.delivererId = delivererId;
	}

}
