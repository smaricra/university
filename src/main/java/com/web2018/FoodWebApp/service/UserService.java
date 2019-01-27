package com.web2018.FoodWebApp.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.web2018.FoodWebApp.model.User;
import com.web2018.FoodWebApp.repo.UserRepo;

public class UserService {
	
	private static UserRepo userRepo;
	
	@Context 
	private static ServletContext servletContext;

	private static Serializer serializer = new Persister();
	private static String tempUrl = "C:/Users/Sava/Documents/Projects/Fax/FoodWebApp/src/main/resources/data/users.xml";
	//private static URL userURL = UserService.class.getResource("/data/users.xml");
	private static String fixedUrl;
	
	public UserService() {
		System.out.println("USER 7");
		try {
			//fixedUrl = userURL.toString().replaceAll("file:/", "");
			//File file = new File(fixedUrl);
			File file = new File(tempUrl);
			userRepo = serializer.read(UserRepo.class, file);
		} catch (Exception e) {
			userRepo = new UserRepo();
		}

	}
	
	public Long add(User user) {
		if(getByEmail(user.getEmail())==null && getByUsername(user.getUsername())==null) {
			user.setId((long) userRepo.getUsers().size());
			user.setActive(true);
			user.setRegistrationDate(new Date());
			userRepo.getUsers().add(user);
			updateData();
			return user.getId();
		} else {
			System.out.println("User with the same email/username already exists!");
			return null;
		}
	}
	
	public void update(User user) {
		for(int i=0; i<userRepo.getUsers().size(); i++) {
			if(userRepo.getUsers().get(i).getId()==user.getId()) {
				userRepo.getUsers().set(i, user);
				updateData();
				System.out.println("User: " + user.getUsername() + " is updated...");
				return;
			}
		}
		System.out.println("User: " + user.getId() + " COULDN'T HAVE BEEN UPDATED...");
	}
	
	public void delete(Long id) {
		for(User u : userRepo.getUsers()) if(u.getId()==id) {
			u.setActive(false);
			updateData();
			System.out.println("User: " + u.getUsername() + " is removed...");
			return;
		}
		System.out.println("User: " + id + " COULDN'T HAVE BEEN REMOVED...");
	}

	public User getById(Long id) {
		for(int i=0; i<userRepo.getUsers().size(); i++) {
			User u = userRepo.getUsers().get(i);
			if(u.getId().equals(id)) return u;
		}
		return null;
	}
	
	public User getByUsername(String username) {
		for(int i=0; i<userRepo.getUsers().size(); i++) {
			User u = userRepo.getUsers().get(i);
			if(u.getUsername().equals(username) && u.isActive()) return u;
		}
		return null;
	}
	
	public User getByEmail(String email) {
		for(int i=0; i<userRepo.getUsers().size(); i++) {
			User u = userRepo.getUsers().get(i);
			if(u.getEmail().equals(email) && u.isActive()) return u;
		}
		return null;
	}
	
	public List<User> getAll() {
		List<User> ret = new ArrayList<>();
		userRepo.getUsers().forEach(u-> {
			if(u.isActive()) ret.add(u);
		});
		return ret;
	}

	private static void updateData() {
		try {
			//File file = new File(fixedUrl);
			File file = new File(tempUrl);
			serializer.write(userRepo, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
