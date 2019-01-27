package com.web2018.FoodWebApp.repo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.simpleframework.xml.ElementList;

import com.web2018.FoodWebApp.model.CustomerRestaurant;

@XmlRootElement(name="root")
public class CustomerRestaurantRepo {
	
	@ElementList(required=false)
	private List<CustomerRestaurant> customerRestaurants;

	public CustomerRestaurantRepo() {
		customerRestaurants = new ArrayList<>();
	}

	public List<CustomerRestaurant> getCustomerRestaurants() {
		return customerRestaurants;
	}

	public void setCustomerRestaurants(List<CustomerRestaurant> customerRestaurants) {
		this.customerRestaurants = customerRestaurants;
	}

}
