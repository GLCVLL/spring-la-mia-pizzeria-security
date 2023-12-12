package org.java.spring;

import java.time.LocalDate;
import java.util.List;

import org.java.spring.db.pojo.Discount;
import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.DiscountService;
import org.java.spring.db.serv.IngredientService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DiscountService discountService;
	
	@Autowired
	private IngredientService ingredientService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	} 
	
	@Override
	public void run(String...args) throws Exception{
		
		// INGREDIENTI PIZZA
		
		Ingredient tomato = new Ingredient("Pomodoro");
		Ingredient mozzarella = new Ingredient("Mozzarella");
		Ingredient mushrooms = new Ingredient("Funghi");
		Ingredient cheese = new Ingredient("Formaggio");
		Ingredient salame = new Ingredient("Salame");
		
		
		ingredientService.save(tomato);
		ingredientService.save(mozzarella);
		ingredientService.save(mushrooms);
		ingredientService.save(cheese);
		ingredientService.save(salame);
		
		// PIZZE
		
        pizzaService.save(new Pizza("Margherita", "Classica pizza margherita", "url_margherita", 8.99f, tomato, mozzarella));
        pizzaService.save(new Pizza("Pepperoni", "Pizza con pepperoni piccanti", "url_pepperoni", 9.99f, tomato, mozzarella, salame));
        pizzaService.save(new Pizza("Quattro Formaggi", "Pizza con quattro tipi di formaggio", "url_quattro_formaggi", 10.99f, mozzarella, cheese));
        pizzaService.save(new Pizza("Vegetariana", "Pizza vegetariana con verdure fresche", "url_vegetariana", 9.49f, mozzarella, mushrooms));
        pizzaService.save(new Pizza("Capricciosa", "Pizza con prosciutto, funghi e carciofi", "url_capricciosa", 11.49f, mozzarella, mushrooms));
        
        List<Pizza> pizzas = pizzaService.findAll();
        
        for (Pizza pizza : pizzas) {
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusDays(7);
            Discount discount = new Discount(startDate, endDate, "Sconto " + pizza.getName(), pizza);
            discountService.save(discount);
        }
	}
}
