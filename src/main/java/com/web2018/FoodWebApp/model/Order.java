package com.web2018.FoodWebApp.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.simpleframework.xml.Element;

import com.web2018.FoodWebApp.core.Constants.ORDER_STATUS;

public class Order extends Model {
	
	@Element
	private Long customerId;
	@Element
	private Date dateAndTime;
	@Element
	private ORDER_STATUS status;
	@Element(required=false)
	private Long delivererId;
	@Element(required=false)
	private String note;
	
	public Order() {
		super();
	}

	public Order(Date dateAndTime, Long customerId, Long delivererId, ORDER_STATUS status, String note) {
		super();
		this.dateAndTime = dateAndTime;
		this.customerId = customerId;
		this.delivererId = delivererId;
		this.status = status;
		this.note = note;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getDelivererId() {
		return delivererId;
	}

	public void setDelivererId(Long delivererId) {
		this.delivererId = delivererId;
	}

	public ORDER_STATUS getStatus() {
		return status;
	}

	public void setStatus(ORDER_STATUS status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
