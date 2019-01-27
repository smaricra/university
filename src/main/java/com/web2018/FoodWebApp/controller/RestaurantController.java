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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import com.google.gson.Gson;
import com.web2018.FoodWebApp.core.AppListener;
import com.web2018.FoodWebApp.core.Constants.PRODUCT_TYPE;
import com.web2018.FoodWebApp.model.Product;
import com.web2018.FoodWebApp.model.Restaurant;

@Path("/restaurant")
public class RestaurantController {
	
	private static Gson gson = new Gson();
	
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") Long id) {
		Restaurant r = AppListener.restaurantService.getById(id);
		String json = gson.toJson(r);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/all")
	public Response getAll() {
		List<Restaurant> restaurants = AppListener.restaurantService.getAll();
		String json = gson.toJson(restaurants);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(String json) {
		Restaurant r = gson.fromJson(json, Restaurant.class);
		Long id = AppListener.restaurantService.add(r);
		if(id!=null) {
			return Response.ok(id, MediaType.TEXT_PLAIN).build();
		} else {
			return Response.status(Status.CONFLICT).build();
		}
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(String json) {
		Restaurant r = gson.fromJson(json, Restaurant.class);
		Long id = AppListener.restaurantService.update(r);
		if(id!=null) {
			return Response.ok().build();
		} else {
			return Response.status(Status.CONFLICT).build();
		}	
	}
	
	@DELETE
	@Path("/{id}")
	public Response add(@PathParam("id")Long id) {
		AppListener.restaurantService.delete(id);
		return Response.ok().build();
	}
	
	@GET
	@Path("/{id}/products/{type}")
	public Response getProductsByType(@PathParam("id") Long id, @PathParam("type") PRODUCT_TYPE type) {
		List<Product> products = AppListener.productService.getByRestaurant(id, type);
		String json = gson.toJson(products);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{id}/products/all")
	public Response getProductsAll(@PathParam("id") Long id) {
		List<Product> products = AppListener.productService.getByRestaurant(id, null);
		String json = gson.toJson(products);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
//	@GET
//	@Path("/{id}/food")
//	public Response getFood(@PathParam("id") Long id) {
//		List<Product> food = AppListener.productService.filter((String) null, id, PRODUCT_TYPE.FOOD, (Double) null,(Double) null); 
//		String json = gson.toJson(food);
//		return Response.ok(json, MediaType.APPLICATION_JSON).build();
//	}
//	
//	@GET
//	@Path("/{id}/baverages")
//	public Response getBaverages(@PathParam("id") Long id) {
//		List<Product> baverages = AppListener.productService.filter("", id, PRODUCT_TYPE.FOOD, 0, 5000); 
//		String json = gson.toJson(baverages);
//		return Response.ok(json, MediaType.APPLICATION_JSON).build();
//	}

}
