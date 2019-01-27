package com.web2018.FoodWebApp.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.simpleframework.xml.Element;

@XmlRootElement(name="orderProduct")
public class OrderProduct extends Model{
	
	@Element
	private Long orderId;
	@Element 
	private Long productId;
	@Element
	private int amount;
	
	public OrderProduct() {
		super();
	}
	
	public OrderProduct(Long orderId, Long productId, int amount) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.amount = amount;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getAmmount() {
		return amount;
	}

	public void setAmmount(int amount) {
		this.amount = amount;
	}
}
