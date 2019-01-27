package com.web2018.FoodWebApp.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.web2018.FoodWebApp.core.AppListener;
import com.web2018.FoodWebApp.model.User;
import com.web2018.FoodWebApp.model.Vehicle;

@Path("/vehicle")
public class VehicleController {
	
	private static Gson gson = new Gson();
	
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") Long id) {
		Vehicle v = AppListener.vehicleService.getById(id);
		String json = gson.toJson(v);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/all")
	public Response getAll() {
		List<Vehicle> vehicles = AppListener.vehicleService.getAll();
		String json = gson.toJson(vehicles);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(String json) {
		Vehicle v = gson.fromJson(json, Vehicle.class);
		return Response.ok(AppListener.vehicleService.add(v), MediaType.TEXT_PLAIN).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(String json) {
		Vehicle v = gson.fromJson(json, Vehicle.class);
		AppListener.vehicleService.update(v);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id")Long id) {
		AppListener.vehicleService.delete(id);
		return Response.ok().build();
	}

}
