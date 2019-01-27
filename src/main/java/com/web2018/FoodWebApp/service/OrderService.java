package com.web2018.FoodWebApp.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.web2018.FoodWebApp.model.Order;
import com.web2018.FoodWebApp.model.Restaurant;
import com.web2018.FoodWebApp.repo.OrderRepo;

public class OrderService {
	
	private static OrderRepo orderRepo;
	
	@Context 
	private static ServletContext servletContext;

	private static Serializer serializer = new Persister();
	private static String tempUrl = "D:\\work\\university\\src\\main\\resources\\data\\orders.xml";
	//private static URL userURL = UserService.class.getResource("/data/orders.xml");
	private static String fixedUrl;
	
	public OrderService() {
		System.out.println("ORDER 4");
		try {
			//fixedUrl = userURL.toString().replaceAll("file:/", "");
			//File file = new File(fixedUrl);
			File file = new File(tempUrl);
			orderRepo = serializer.read(OrderRepo.class, file);
		} catch (Exception e) {
			orderRepo = new OrderRepo();
		}

	}
	
	public Long add(Order order) {
		order.setId((long) orderRepo.getOrders().size());
		order.setActive(true);
		order.setDateAndTime(new Date());
		orderRepo.getOrders().add(order);
		updateData();
		return order.getId();
	}
	
	public void update(Order order) {
		for(int i=0; i<orderRepo.getOrders().size(); i++) {
			if(orderRepo.getOrders().get(i).getId()==order.getId()) {
				order.setDateAndTime(new Date());
				orderRepo.getOrders().set(i, order);
				updateData();
			}
		}
	}
	
	public void delete(Long id) {
		for(int i=0; i<orderRepo.getOrders().size(); i++) {
			if(orderRepo.getOrders().get(i).getId()==id) {
				orderRepo.getOrders().get(i).setActive(false);
				updateData();
			}
		}
	}
	
	public Order getById(Long id) {
		for(int i=0; i<orderRepo.getOrders().size(); i++) {
			if(orderRepo.getOrders().get(i).getId()==id && orderRepo.getOrders().get(i).isActive()) {
				return orderRepo.getOrders().get(i);
			}
		}
		return null;
	}
	
	public List<Order> getByUserId(Long userId) {
		List<Order> ret = new ArrayList<>();
		orderRepo.getOrders().forEach(o-> {
			if(o.isActive() && (o.getDelivererId()==userId || o.getCustomerId()==userId)) ret.add(o);
		});
		return ret;
	}
	
	public List<Order> getAll() {
		List<Order> ret = new ArrayList<>();
		orderRepo.getOrders().forEach(o-> {
			if(o.isActive()) ret.add(o);
		});
		return ret;
	}
	
	private static void updateData() {
		try {
			//File file = new File(fixedUrl);
			File file = new File(tempUrl);
			serializer.write(orderRepo, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
