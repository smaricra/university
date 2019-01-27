package com.web2018.FoodWebApp.model;

import org.simpleframework.xml.Element;

public class Model {
	
	@Element
	private Long id;
	@Element
	private boolean active;
	
	public Model() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
