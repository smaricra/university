package com.web2018.FoodWebApp.model;


import org.simpleframework.xml.Element;

import com.web2018.FoodWebApp.core.Constants;
import com.web2018.FoodWebApp.core.Constants.RESTAURANT_TYPE;

public class Restaurant extends Model{
	
	@Element
	private String name;
	@Element
	private String address;
	@Element
	private RESTAURANT_TYPE category;
	
	public Restaurant() {
		super();
	}

	public Restaurant(String name, String address, RESTAURANT_TYPE category) {
		super();
		this.name = name;
		this.address = address;
		this.category = category;
	}

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

	public RESTAURANT_TYPE getCategory() {
		return category;
	}

	public void setCategory(RESTAURANT_TYPE category) {
		this.category = category;
	}

	
}
