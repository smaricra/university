/*
package com.web2018.FoodWebApp.model;

import com.web2018.FoodWebApp.model.Vehicle;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.simpleframework.xml.Element;

import com.web2018.FoodWebApp.model.Order;

public class Deliverer extends User {

	@Element
	private Vehicle vehicleId;
	private List<Order> orders;
	
	public Deliverer() {
		super();
		this.setRole("DELIVERER");
		// TODO Auto-generated constructor stub
	}
	public Deliverer(String username, String password, String name, String surname, String phoneNumber,
			String email, Date registrationDate) {
		this(username, password, name, surname, phoneNumber, email, registrationDate, null, null);
	}
	public Deliverer(String username, String password, String name, String surname, String phoneNumber,
			String email, Date registrationDate, Vehicle vehicleId, List<Order> orders) {
		super(username, password, name, surname, "DELIVERER", phoneNumber, email, registrationDate);
		this.vehicleId = vehicleId;
		this.orders = orders;
		// TODO Auto-generated constructor stub
	}
	/*
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
}
*/