package com.web2018.FoodWebApp.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.web2018.FoodWebApp.model.Vehicle;
import com.web2018.FoodWebApp.repo.VehicleRepo;

public class VehicleService {
	
	private static VehicleRepo vehicleRepo;
	
	@Context 
	private static ServletContext servletContext;

	private static Serializer serializer = new Persister();
	private static String tempUrl = "D:\\work\\university\\src\\main\\resources\\data\\vehicles.xml";
	//private static URL userURL = UserService.class.getResource("/data/vehicles.xml");
	private static String fixedUrl;
	
	public VehicleService() {
		System.out.println("VEHICLE 1");
		try {
			//fixedUrl = userURL.toString().replaceAll("file:/", "");
			//File file = new File(fixedUrl);
			File file = new File(tempUrl);
			vehicleRepo = serializer.read(VehicleRepo.class, file);
		} catch (Exception e) {
			vehicleRepo = new VehicleRepo();
		}

	}
	
	public Long add(Vehicle vehicle) {
		vehicle.setId((long) vehicleRepo.getVehicles().size());
		vehicle.setActive(true);
		vehicleRepo.getVehicles().add(vehicle);
		updateData();
		return vehicle.getId();
	}
	
	public void update(Vehicle vehicle) {
		for(int i=0; i<vehicleRepo.getVehicles().size(); i++) {
			if(vehicleRepo.getVehicles().get(i).getId()==vehicle.getId()) {
				vehicleRepo.getVehicles().set(i, vehicle);
				updateData();
			}
		}
	}
	
	public void delete(Long id) {
		for(int i=0; i<vehicleRepo.getVehicles().size(); i++) {
			if(vehicleRepo.getVehicles().get(i).getId()==id) {
				vehicleRepo.getVehicles().get(i).setActive(false);
				updateData();
			}
		}
	}
	
	public Vehicle getById(Long id) {
		for(int i=0; i<vehicleRepo.getVehicles().size(); i++) {
			if(vehicleRepo.getVehicles().get(i).getId()==id) {
				return vehicleRepo.getVehicles().get(i);
			}
		}
		return null;
	}
	
	public List<Vehicle> getAll() {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicleRepo.getVehicles().forEach(v -> {
			if(v.isActive()) vehicles.add(v);
		});
		return vehicles;
	}
	
	public List<Vehicle> getAvailable() {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicleRepo.getVehicles().forEach(v -> {
			if(v.isActive() && v.isAvailable()) vehicles.add(v);
		});
		return vehicles;
	}
	
	public List<Vehicle> getUnavailable() {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicleRepo.getVehicles().forEach(v -> {
			if(v.isActive() && !v.isAvailable()) vehicles.add(v);
		});
		return vehicles;
	}
	
	private static void updateData() {
		try {
			//File file = new File(fixedUrl);
			File file = new File(tempUrl);
			serializer.write(vehicleRepo, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
