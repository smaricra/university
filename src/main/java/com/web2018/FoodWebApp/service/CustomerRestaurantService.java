package com.web2018.FoodWebApp.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.web2018.FoodWebApp.model.CustomerRestaurant;
import com.web2018.FoodWebApp.model.Restaurant;
import com.web2018.FoodWebApp.repo.CustomerRestaurantRepo;

public class CustomerRestaurantService {
	
	private static CustomerRestaurantRepo customerRestaurantRepo;
	
	@Context 
	private static ServletContext servletContext;

	private static Serializer serializer = new Persister();
	private static String tempUrl = "C:/Users/Sava/Documents/Projects/Fax/FoodWebApp/src/main/resources/data/customer_restaurants.xml";
	//private static URL userURL = UserService.class.getResource("/data/customer_restaurants.xml");
	private static String fixedUrl;
	
	public CustomerRestaurantService() {
		System.out.println("CUSTOMER_RESTRAURANT 2");
		try {
			//fixedUrl = userURL.toString().replaceAll("file:/", "");
			//File file = new File(fixedUrl);
			File file = new File(tempUrl);
			customerRestaurantRepo = serializer.read(CustomerRestaurantRepo.class, file);
		} catch (Exception e) {
			customerRestaurantRepo = new CustomerRestaurantRepo();
		}

	}
	
	public void add(CustomerRestaurant customerRestaurant) {
		customerRestaurant.setId((long) customerRestaurantRepo.getCustomerRestaurants().size());
		customerRestaurant.setActive(true);
		customerRestaurantRepo.getCustomerRestaurants().add(customerRestaurant);
		updateData();
	}
	
	
	public void delete(Long id) {
		for(int i=0; i< customerRestaurantRepo.getCustomerRestaurants().size(); i++) {
			if(customerRestaurantRepo.getCustomerRestaurants().get(i).getId()==id) {
				customerRestaurantRepo.getCustomerRestaurants().get(i).setActive(false);
				System.out.println("CustomerRestaurant: " + id + " is removed...");
				return;
			}
		}
		System.out.println("CustomerRestaurant: " + id + " COULDN'T HAVE BEEN REMOVED...");
	}
	
	public CustomerRestaurant getById(Long id) {
		for(int i=0; i<customerRestaurantRepo.getCustomerRestaurants().size(); i++) {
			CustomerRestaurant r = customerRestaurantRepo.getCustomerRestaurants().get(i);
			if(r.getId().equals(id)) return r;
		}
		return null;
	}
	
	public List<Long> getRestaurantsIdsByUser(Long userId) {
		List<Long> ret = new ArrayList<>();
		customerRestaurantRepo.getCustomerRestaurants().forEach(cr-> {
			if(cr.isActive() && cr.getCustomerId()==userId) ret.add(cr.getRestaurantId());
		});
		return ret;
	}
	
	private static void updateData() {
		try {
			//File file = new File(fixedUrl);
			File file = new File(tempUrl);
			serializer.write(customerRestaurantRepo, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
