package com.web2018.FoodWebApp.repo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.simpleframework.xml.ElementList;

import com.web2018.FoodWebApp.model.Restaurant;

@XmlRootElement(name="root")
public class RestaurantRepo {
	
	@ElementList(required=false)
	List<Restaurant> restaurants;
	
	public RestaurantRepo() {
		restaurants = new ArrayList<>();
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
}
