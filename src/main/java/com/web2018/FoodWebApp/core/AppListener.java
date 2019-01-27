package com.web2018.FoodWebApp.core;

import javax.servlet.ServletContextEvent;

import com.web2018.FoodWebApp.core.Constants.RESTAURANT_TYPE;
import com.web2018.FoodWebApp.model.Restaurant;
import com.web2018.FoodWebApp.service.CommentService;
import com.web2018.FoodWebApp.service.CustomerRestaurantService;
import com.web2018.FoodWebApp.service.OrderProductService;
import com.web2018.FoodWebApp.service.OrderService;
import com.web2018.FoodWebApp.service.ProductService;
import com.web2018.FoodWebApp.service.RestaurantService;
import com.web2018.FoodWebApp.service.UserService;
import com.web2018.FoodWebApp.service.VehicleService;

public class AppListener implements javax.servlet.ServletContextListener {

	public static CommentService commentService;
	public static CustomerRestaurantService customerRestaurantService;
	public static OrderProductService orderProductService;
	public static OrderService orderService;
	public static ProductService productService;
	public static RestaurantService restaurantService;
	public static UserService userService;
	public static VehicleService vehicleService;
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("STARTED!!!!!!!!!!!!!!!!!!");
		commentService = new CommentService();
		customerRestaurantService= new CustomerRestaurantService();
		orderProductService = new OrderProductService();
		orderService = new OrderService();
		productService = new ProductService();
		restaurantService = new RestaurantService();
		userService = new UserService();
		vehicleService = new VehicleService();
		
		/**
		Restaurant restaurant1 = new Restaurant("bakalala 1", "Address 1", Constants.RESTAURANT_TYPE.DOMESTIC);
		Restaurant restaurant2 = new Restaurant("sakalfff 2", "Address 2", Constants.RESTAURANT_TYPE.CHINESE);
		Restaurant restaurant3 = new Restaurant("takaklll 3", "Addr2ess 3", Constants.RESTAURANT_TYPE.BARBECUE);
		Restaurant restaurant4 = new Restaurant("akaaaa 4", "Address 4", Constants.RESTAURANT_TYPE.DOMESTIC);
		Restaurant restaurant5 = new Restaurant("adadas 5", "Address 5", Constants.RESTAURANT_TYPE.PASTRY_SHOP);
		Restaurant restaurant6 = new Restaurant("ffdfd 6", "Address 6", Constants.RESTAURANT_TYPE.DOMESTIC);
	
		restaurantService.add(restaurant1);
		restaurantService.add(restaurant2);
		restaurantService.add(restaurant3);
		restaurantService.add(restaurant4);
		restaurantService.add(restaurant5);
		restaurantService.add(restaurant6);
		
		System.out.println(restaurantService.filer("", "2e", null).size());
		*/
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("ENDED!!!!!!!!!!!!!!!!!!");
	}
	}
