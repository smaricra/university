package com.web2018.FoodWebApp.core;

public class Constants {
	
	public enum USER_TYPE {
		CUSTOMER, DELIVERER, ADMIN
	}

	public enum RESTAURANT_TYPE {
		DOMESTIC, BARBECUE, CHINESE, INDIAN, PASTRY_SHOP, PIZZERIA
	}
	
	public enum PRODUCT_TYPE {
		FOOD, BEVERAGE
	}
	
	public enum PRODUCT_UNIT {
		G, ML, PIECE
	}
	
	public enum VEHICLE_TYPE {
		BIKE, SCOOTER, CAR
	}
	
	public enum ORDER_STATUS {
		ORDERED, DELIVERING, CANCELED, DELIVERED
	}
	
	public static Boolean TESTING = false;

}
