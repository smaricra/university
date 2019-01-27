package com.web2018.FoodWebApp.model;

import org.simpleframework.xml.Element;

public class CustomerRestaurant extends Model {
	
	@Element
	private Long customerId;
	@Element
	private Long restaurantId;
	
	public CustomerRestaurant() {	
		super();
	}
	
	public CustomerRestaurant(Long customerId, Long restaurantId) {
		super();
		this.customerId = customerId;
		this.restaurantId = restaurantId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
}
