package com.web2018.FoodWebApp.repo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.simpleframework.xml.ElementList;

import com.web2018.FoodWebApp.model.Order;

@XmlRootElement(name="root")
public class OrderRepo {
	
	@ElementList(required=false)
	List<Order> orders;
	
	public OrderRepo() {
		orders = new ArrayList<>();
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
