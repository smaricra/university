package com.web2018.FoodWebApp.repo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.simpleframework.xml.ElementList;

import com.web2018.FoodWebApp.model.OrderProduct;

@XmlRootElement(name="root")
public class OrderProductRepo {
	
	@ElementList(required=false)
	private List<OrderProduct> orderProducts;
	
	public OrderProductRepo() {
		orderProducts = new ArrayList<>();
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

}
