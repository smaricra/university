package com.web2018.FoodWebApp.model;

import java.util.Date;

import org.simpleframework.xml.Element;

import com.web2018.FoodWebApp.core.Constants;
import com.web2018.FoodWebApp.core.Constants.USER_TYPE;

public class User extends Model {
	
	@Element
	private String username;
	@Element
	private String password;
	@Element
	private String name;
	@Element
	private String surname;
	@Element
	private USER_TYPE role;
	@Element
	private String phoneNumber;
	@Element
	private String email;
	@Element
	private Date registrationDate;
	
	public User() {
		super();
	}
	
	public User(String username, String password, String name, String surname, USER_TYPE role, String phoneNumber,
			String email) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	public USER_TYPE getRole() {
		return role;
	}

	public void setRole(USER_TYPE role) {
		this.role = role;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

}
