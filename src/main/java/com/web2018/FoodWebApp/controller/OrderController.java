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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.web2018.FoodWebApp.core.AppListener;
import com.web2018.FoodWebApp.model.Order;
import com.web2018.FoodWebApp.model.OrderProduct;
import com.web2018.FoodWebApp.model.Vehicle;

@Path("/order")
public class OrderController {
	
	private static Gson gson = new Gson();
	
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") Long id) {
		Order o = AppListener.orderService.getById(id);
		String json = gson.toJson(o);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/all")
	public Response getAll() {
		List<Order> orders = AppListener.orderService.getAll();
		String json = gson.toJson(orders);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(String json) {
		JsonParser parser = new JsonParser();
		JsonObject o = parser.parse(json).getAsJsonObject();
		JsonElement orderJson = o.get("order");
		JsonElement orderProductJson = o.get("orderProduct");
		Order order = gson.fromJson(orderJson, Order.class);
		List<OrderProduct> orderProducts = gson.fromJson(orderProductJson, List.class);
		int bla = orderProducts.get(0).getAmmount();
//		AppListener.orderProductService.add(orderProducts);
//		return Response.ok(AppListener.orderService.add(order), MediaType.TEXT_PLAIN).build();
		return null;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(String json) {
		Order o = gson.fromJson(json, Order.class);
		AppListener.orderService.update(o);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id")Long id) {
		AppListener.orderService.delete(id);
		return Response.ok().build();
	}
	
}
