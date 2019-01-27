package com.web2018.FoodWeppApp;

import java.util.ArrayList;
import java.util.List;

import com.web2018.FoodWebApp.core.Constants;
import com.web2018.FoodWebApp.model.Restaurant;
import com.web2018.FoodWebApp.repo.RestaurantRepo;

public class MockUpData {
	
	public static Boolean testing;
	public static Restaurant restaurant0 = new Restaurant("Restaurant 0", "Address 0", Constants.RESTAURANT_TYPE.DOMESTIC);
	public static Restaurant restaurant1 = new Restaurant("Restaurant 1", "Address 1", Constants.RESTAURANT_TYPE.CHINESE);
	public static Restaurant restaurant2 = new Restaurant("Restaurant 2", "Address 2", Constants.RESTAURANT_TYPE.BARBECUE);
	public static Restaurant restaurant3 = new Restaurant("Restaurant 3", "Address 3", Constants.RESTAURANT_TYPE.DOMESTIC);
	public static Restaurant restaurant4 = new Restaurant("Restaurant 4", "Address 4", Constants.RESTAURANT_TYPE.PASTRY_SHOP);
	public static Restaurant restaurant5 = new Restaurant("Restaurant 5", "Address 5", Constants.RESTAURANT_TYPE.DOMESTIC);
	public static RestaurantRepo restaurantRepo;
	
	static {
		testing = true;
		
		restaurant0.setId(0l);
		restaurant1.setId(1l);
		restaurant2.setId(2l);
		restaurant3.setId(3l);
		restaurant4.setId(4l);
		restaurant5.setId(5l);
		restaurant0.setActive(true);
		restaurant1.setActive(true);
		restaurant2.setActive(true);
		restaurant3.setActive(true);
		restaurant4.setActive(true);
		restaurant5.setActive(true);

		List<Restaurant> restaurants = new ArrayList<>();
		restaurants.add(restaurant0);
		restaurants.add(restaurant1);
		restaurants.add(restaurant2);
		restaurants.add(restaurant3);
		restaurants.add(restaurant4);
		restaurants.add(restaurant5);
		
		restaurantRepo = new RestaurantRepo();
		restaurantRepo.setRestaurants(restaurants);
	}
	
	
}
