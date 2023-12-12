package org.java.spring.controller;

import org.java.spring.db.serv.PizzaService;
import org.java.spring.dto.PizzaDiscountDTO;

import java.time.LocalDate;
import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.pojo.Discount;
import org.java.spring.db.serv.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DiscountController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DiscountService discountService;
	
	
	@GetMapping("/pizzas/discount")
	public String getDiscountForm(Model model) {
		
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		
		return "discount-form";
	}
	@PostMapping("/pizzas/discount")
	public String storeDiscount(
			@ModelAttribute PizzaDiscountDTO pizzaDiscountDTO
		) {
		
		Pizza pizza = pizzaService.findById(pizzaDiscountDTO.getPizza_id());
		
		Discount discount = new Discount(
				LocalDate.now(),
				LocalDate.now().plusDays(7),
				pizzaDiscountDTO.getTitle(),
				pizza);
		
		discountService.save(discount);
		
		return "redirect:/";
	}

}
