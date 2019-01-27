package com.web2018.FoodWebApp.controller;

import java.awt.image.LookupTable;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Singleton;
import javax.swing.LookAndFeel;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jaxb.internal.XmlCollectionJaxbProvider.App;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.web2018.FoodWebApp.core.AppListener;
import com.web2018.FoodWebApp.core.Constants.USER_TYPE;
import com.web2018.FoodWebApp.core.EmailValidator;
import com.web2018.FoodWebApp.model.User;
import com.web2018.FoodWebApp.model.Vehicle;
import com.web2018.FoodWebApp.service.RestaurantService;
import com.web2018.FoodWebApp.service.UserService;

@Path("/user")
public class UserController {
	
	private static Gson gson = new Gson();
	private EmailValidator emailValidator = new EmailValidator();
	
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") Long id) {
		User u = AppListener.userService.getById(id);
		String json = gson.toJson(u);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/all")
	public Response getAll() {
		List<User> users = AppListener.userService.getAll();
		String json = gson.toJson(users);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(String json) {
		User u = gson.fromJson(json, User.class);
		return Response.ok(AppListener.userService.add(u), MediaType.TEXT_PLAIN).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(String json) {
		User u = gson.fromJson(json, User.class);
		AppListener.userService.update(u);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id")Long id) {
		AppListener.userService.delete(id);
		return Response.ok().build();
	}
	
//	@GET
//	@Path("/{id}/vehicle")
//	public Response getVehicleByUserId(@PathParam("id") Long id) {
//		Vehicle vehicle = AppListener.vehicleService.getByDelivererId(id);
//		String json = gson.toJson(vehicle);
//		return Response.ok(json, MediaType.APPLICATION_JSON).build();
//	}
	
	@POST
	@Path("/login") 
	public Response login(String json) {
		JsonObject userLogin = gson.fromJson(json, JsonObject.class);
		if((userLogin.get("username")!=null && userLogin.get("password")!=null)) {
			User user = AppListener.userService.getByUsername(userLogin.get("username").getAsString());
			if(user!=null && user.getPassword().equals(userLogin.get("password").getAsString())) {
				JsonObject jsonRet = new JsonObject();
				jsonRet.addProperty("username", user.getUsername());
				jsonRet.addProperty("role", user.getRole().toString());
				jsonRet.addProperty("id", user.getId().toString());
				return Response.ok(jsonRet.toString(), MediaType.APPLICATION_JSON).build();
			}
		}
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("Login failed. Check username and password and try again.")
				.type(MediaType.TEXT_PLAIN).build();
		
	}
	
	@POST
	@Path("/register")
	public Response register(String json) {
		JsonObject userRegister = gson.fromJson(json, JsonObject.class);
		if(userRegister.get("name")!=null && userRegister.get("surname")!=null && 
				userRegister.get("phone")!=null && userRegister.get("email")!=null &&
				userRegister.get("username")!=null && userRegister.get("password")!=null &&
				userRegister.get("password2")!=null &&
				!userRegister.get("name").getAsString().equals("") && !userRegister.get("surname").getAsString().equals("") && 
				!userRegister.get("phone").getAsString().equals("") && !userRegister.get("email").getAsString().equals("") &&
				!userRegister.get("username").getAsString().equals("") && !userRegister.get("password").getAsString().equals("") &&
				!userRegister.get("password2").getAsString().equals("")) {
			
			String email = userRegister.get("email").getAsString();
			if(!emailValidator.validateEmail(email)) {
				return Response.status(Response.Status.BAD_REQUEST)
						.entity("Email address is not valid.")
						.type(MediaType.TEXT_PLAIN).build();
			}
			
			if(AppListener.userService.getByEmail(email)!=null) {
				return Response.status(Response.Status.BAD_REQUEST)
						.entity("Email already exists.")
						.type(MediaType.TEXT_PLAIN).build();
			}
			
			String username = userRegister.get("username").getAsString();
			if(AppListener.userService.getByUsername(username)!=null) {
				return Response.status(Response.Status.BAD_REQUEST)
						.entity("Username already exists.")
						.type(MediaType.TEXT_PLAIN).build();
			}
			
			String password = userRegister.get("password").getAsString();
			String password2 = userRegister.get("password2").getAsString();
			if(!password.equals(password2)) {
				return Response.status(Response.Status.BAD_REQUEST)
						.entity("Passwords do not match.")
						.type(MediaType.TEXT_PLAIN).build();
			}
			
			String name = userRegister.get("name").getAsString();
			String surname = userRegister.get("surname").getAsString();
			String phoneNumber = userRegister.get("phone").getAsString();
			
			User user = new User(username, password, name, surname, USER_TYPE.CUSTOMER, phoneNumber, email);
			Long id = AppListener.userService.add(user);
			if(id!=null) {
				return Response.status(Response.Status.CREATED)
						.entity(id)
						.type(MediaType.TEXT_PLAIN).build();
			}
		}
		
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("Registration failed. Check all the fields and try again.")
				.type(MediaType.TEXT_PLAIN).build();
	}

}
