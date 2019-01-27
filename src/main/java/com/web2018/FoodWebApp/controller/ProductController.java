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
import com.web2018.FoodWebApp.model.Product;

@Path("/product")
public class ProductController {
	
	private static Gson gson = new Gson();
	
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") Long id) {
		Product p = AppListener.productService.getById(id);
		String json = gson.toJson(p);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/all")
	public Response getAll() {
		List<Product> products = AppListener.productService.getAll();
		String json = gson.toJson(products);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(String json) {
		Product p = gson.fromJson(json, Product.class);
		return Response.ok(AppListener.productService.add(p), MediaType.TEXT_PLAIN).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(String json) {
		Product p = gson.fromJson(json, Product.class);
		AppListener.productService.update(p);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id")Long id) {
		AppListener.productService.delete(id);
		return Response.ok().build();
	}

	@GET
	@Path("/bestTen")
	public Response getBestTen() {
		List<Product> products = AppListener.orderProductService.getAll();
		String json = gson.toJson(products);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}

}
