package com.web2018.FoodWebApp.repo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.simpleframework.xml.ElementList;

import com.web2018.FoodWebApp.model.Vehicle;

@XmlRootElement(name="root")
public class VehicleRepo {
	
	@ElementList(required=false)
	List<Vehicle> vehicles;
	
	public VehicleRepo() {
		vehicles = new ArrayList<>();
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

}
