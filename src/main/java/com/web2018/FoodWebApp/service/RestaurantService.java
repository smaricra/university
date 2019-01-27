package com.web2018.FoodWebApp.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.web2018.FoodWebApp.core.Constants;
import com.web2018.FoodWebApp.core.Constants.RESTAURANT_TYPE;
import com.web2018.FoodWebApp.model.Restaurant;
import com.web2018.FoodWebApp.repo.RestaurantRepo;

public class RestaurantService {
	
	public static RestaurantRepo restaurantRepo;
	
	@Context 
	private static ServletContext servletContext;

	private static Serializer serializer = new Persister();
	private static String tempUrl = "D:\\work\\university\\src\\main\\resources\\data\\restaurants.xml";
	//private static URL restaurantUrl = RestaurantService.class.getResource("/data/restaurants.xml");
	private static String fixedUrl;
	
	static {
		if(Constants.TESTING==false) {
			try {
				//fixedUrl = restaurantUrl.toString().replaceAll("file:/", "");
				//File file = new File(fixedUrl);
				File file = new File(tempUrl);
				restaurantRepo = serializer.read(RestaurantRepo.class, file);
			} catch (Exception e) {
				restaurantRepo = new RestaurantRepo();
			}
		}
	}
	
	public Long add(Restaurant restaurant) {
		for(int i=0; i<restaurantRepo.getRestaurants().size(); i++) {
			if(restaurantRepo.getRestaurants().get(i).getName().equals(restaurant.getName())) return null;
		}
		restaurant.setId((long) restaurantRepo.getRestaurants().size());
		restaurant.setActive(true);
		restaurantRepo.getRestaurants().add(restaurant);
		updateData();
		return restaurant.getId();
	}
	
	public Long update(Restaurant restaurant) {
		for(int i=0; i< restaurantRepo.getRestaurants().size(); i++) {
			if(restaurantRepo.getRestaurants().get(i).getId()==restaurant.getId()) {
				if(!restaurantRepo.getRestaurants().get(i).getName().equals(restaurant.getName())) {
					for(int j=0; j<restaurantRepo.getRestaurants().size(); j++) {
						if(restaurantRepo.getRestaurants().get(j).getId()!=restaurant.getId() &&
								restaurantRepo.getRestaurants().get(j).getName().equals(restaurant.getName())) {
							return null;
						}
					}
				}
				restaurantRepo.getRestaurants().set(i, restaurant);
				System.out.println("Restaurant: " + restaurant.getName() + " is updated...");
				updateData();
				return restaurant.getId();
			}
		}
		System.out.println("Restaurant: " + restaurant.getName() + " COULDN'T HAVE BEEN UPDATED...");
		return null;
	}
	
	public void delete(Long id) {
		for(int i=0; i< restaurantRepo.getRestaurants().size(); i++) {
			if(restaurantRepo.getRestaurants().get(i).getId()==id) {
				Restaurant r = restaurantRepo.getRestaurants().get(i);
				r.setActive(false);
				restaurantRepo.getRestaurants().set(i, r);
				System.out.println("Restaurant: " + id + " is removed...");
				updateData();
				return;
			}
		}
		System.out.println("Restaurant: " + id + " COULDN'T HAVE BEEN REMOVED...");
	}
	
	public Restaurant getById(Long id) {
		for(int i=0; i<restaurantRepo.getRestaurants().size(); i++) {
			Restaurant r = restaurantRepo.getRestaurants().get(i);
			if(r.getId().equals(id)) return r;
		}
		return null;
	}
	
	public List<Restaurant> filer(String name, String address, RESTAURANT_TYPE type) {
		List<Restaurant> ret = new ArrayList<>();
		for(Restaurant r : restaurantRepo.getRestaurants()) {
			if(type==null && r.getName().contains(name) && r.getAddress().contains(address)) {
				ret.add(r);
			}
			else if(r.getName().contains(name) && r.getAddress().contains(address) && r.getCategory()==type) {
				ret.add(r);
			}
		}
		return ret;
	}
	
	public List<Restaurant> getAll() {
		List<Restaurant> ret = new ArrayList<>();
		restaurantRepo.getRestaurants().forEach(r-> {
			if(r.isActive()) ret.add(r);
		});
		return ret;
	}

	public static void updateData() {
		if(Constants.TESTING==false) {
			try {
				//File file = new File(fixedUrl);
				File file = new File(tempUrl);
				serializer.write(restaurantRepo, file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
