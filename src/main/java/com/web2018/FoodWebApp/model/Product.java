package com.web2018.FoodWebApp.model;

import org.simpleframework.xml.Element;

import com.web2018.FoodWebApp.core.Constants.PRODUCT_TYPE;
import com.web2018.FoodWebApp.core.Constants.PRODUCT_UNIT;

public class Product extends Model {
	
	@Element
	private String name;
	@Element
	private PRODUCT_TYPE type;
	@Element
	private double price;
	@Element
	private int quantity;
	@Element
	private PRODUCT_UNIT unit;
	@Element
	private Long restaurantId;
	@Element(required=false)
	private String description;
	
	public Product() {
		super();
	}

	public Product(String name, PRODUCT_TYPE type, double price, String description, int quantity, PRODUCT_UNIT unit, Long restaurantId) {
		super();
		this.name = name;
		this.type = type;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.setUnit(unit);
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	
	}

	public PRODUCT_TYPE getType() {
		return type;
	}

	public void setType(PRODUCT_TYPE type) {
		this.type = type;
	}

	public PRODUCT_UNIT getUnit() {
		return unit;
	}

	public void setUnit(PRODUCT_UNIT unit) {
		this.unit = unit;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

}
