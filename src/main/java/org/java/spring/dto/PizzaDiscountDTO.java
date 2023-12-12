package org.java.spring.dto;

public class PizzaDiscountDTO {
	
	private int pizza_id;
	private String title;
	
	// GETTER & SETTER
	
	public int getPizza_id() {
		return pizza_id;
	}
	public void setPizza_id(int pizza_id) {
		this.pizza_id = pizza_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		
		return getPizza_id() + " - " + getTitle();
	}

}
