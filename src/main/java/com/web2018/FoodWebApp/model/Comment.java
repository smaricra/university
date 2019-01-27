package com.web2018.FoodWebApp.model;

import java.util.Date;


import org.simpleframework.xml.Element;

public class Comment extends Model {
	
	@Element
	private Long userId;
	@Element
	private Long orderId;
	@Element
	private String description;
	@Element
	private Date date;
	
	public Comment() {
		super();
	}

	public Comment(Long userId, Long orderId, String description, Date date) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.description = description;
		this.date = date;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
