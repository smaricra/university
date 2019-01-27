package com.web2018.FoodWeppApp.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.web2018.FoodWebApp.core.Constants;
import com.web2018.FoodWebApp.model.Restaurant;
import com.web2018.FoodWebApp.service.RestaurantService;
import com.web2018.FoodWeppApp.MockUpData;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestaurantServiceTest {
	
	public static RestaurantService restaurantService;
	
	@Before
	public void setUp() {
		Constants.TESTING = true;
		MockUpData mockUpData = new MockUpData();
		restaurantService = new RestaurantService();
		restaurantService.restaurantRepo = MockUpData.restaurantRepo;
	}
	
	@Test
	public void test1GetById() {
		Restaurant restaurant = restaurantService.getById(3L);
		assertEquals(Long.valueOf("3"), restaurant.getId());
		assertEquals("Restaurant 3", restaurant.getName());
		assertEquals("Address 3", restaurant.getAddress());
		assertEquals(Constants.RESTAURANT_TYPE.DOMESTIC, restaurant.getCategory());	
	}
	
	@Test
	public void test2GetAll() {
		List<Restaurant> restaurants = restaurantService.getAll();
		assertEquals(MockUpData.restaurantRepo.getRestaurants().size(), restaurants.size());
	}
	
	@Test
	public void test3Add() {
		Restaurant restaurant = new Restaurant("newRestaurant", "newAddress", Constants.RESTAURANT_TYPE.PIZZERIA);
		restaurantService.add(restaurant);
		Restaurant r = restaurantService.getById((long) (restaurantService.restaurantRepo.getRestaurants().size()-1));
		assertEquals(7, restaurantService.getAll().size());
		assertEquals(restaurant.getName(), r.getName());
		assertEquals(restaurant.getAddress(), r.getAddress());
		assertEquals(restaurant.getCategory(), r.getCategory());
	}
	
	@Test
	public void test4Update() {
		Restaurant restaurant = new Restaurant("newRestaurant", "newAddress", Constants.RESTAURANT_TYPE.PIZZERIA);
		restaurant.setId(1L);
		restaurant.setActive(true);
		restaurantService.update(restaurant);
		Restaurant updated = restaurantService.getById(1L);
		assertEquals(7, restaurantService.getAll().size());
		assertEquals(restaurant.getName(), updated.getName());
		assertEquals(restaurant.getAddress(), updated.getAddress());
		assertEquals(restaurant.getCategory(), updated.getCategory());
	}
	
	@Test
	public void test5Delete() {
		restaurantService.delete(5L);
		assertEquals(6, restaurantService.getAll().size());
	}
	
	@Test
	public void test6Filter() {
		assertEquals(6, restaurantService.getAll().size());
	}
	

}
