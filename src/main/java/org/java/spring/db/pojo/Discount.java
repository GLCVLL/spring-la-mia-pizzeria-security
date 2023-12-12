package org.java.spring.db.pojo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Discount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	 private LocalDate startDate;
	 private LocalDate endDate;
	 private String title;
	 
	 @ManyToOne
	 private Pizza pizza;
	 
	 public Discount() {}
	 public Discount(LocalDate startdate, LocalDate endDate, String title, Pizza pizza) {
			setStartDate(startdate);
			setEndDate(endDate);
			setTitle(title);
			setPizza(pizza);
	 }
	 
    // GETTER & SETTER
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	 
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getTitle() + " - " + getStartDate() + " - " + getEndDate();
	} 

}
