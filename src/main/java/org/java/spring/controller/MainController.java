package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.IngredientService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class MainController {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping
	public String getPizzas(Model model, @RequestParam(required = false) String name) {
		List<Pizza> pizzas = name == null
				? pizzaService.findAll()
			    : pizzaService.findByName(name);
		
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("name", name);
		
		return "pizzas";
	}
	
	@GetMapping("/pizzas/{id}")
	public String getPizza(Model model,
			@PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
	    String randomImageUrl = "https://picsum.photos/200/300?random=" + Math.random();

		model.addAttribute("pizza", pizza);
	    model.addAttribute("randomImageUrl", randomImageUrl);

		
		return "pizza";
	}
	

    @GetMapping("/pizzas/new")
    public String createPizzaForm(Model model) {
        Pizza pizza = new Pizza();
        List<Ingredient> ingredients = ingredientService.findAll();
        model.addAttribute("pizza", pizza);
        model.addAttribute("ingredients", ingredients);
        return "newPizza";
    }

    @PostMapping("/pizzas/new")
    public String createPizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model) {
    	return savePizza(pizza, bindingResult, model);
    }
    
    @GetMapping("/pizzas/edit/{id}")
    public String editPizza(Model model, @PathVariable int id) {
        Pizza pizza = pizzaService.findById(id);
        List<Ingredient> ingredients = ingredientService.findAll();
        
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("pizza", pizza);
        
        return "newPizza";
    }

    @PostMapping("/pizzas/edit/{id}")
    public String updatePizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model) {
        return savePizza(pizza, bindingResult, model);
    }
    
	@PostMapping("/pizzas/delete/{id}")
	public String deletePizza(@PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		pizzaService.delete(pizza);
		
		return "redirect:/";
	}
    
    private String savePizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "newPizza";
        }

        try {
            pizzaService.save(pizza);
        } catch (Exception e) {
            bindingResult.addError(new FieldError("pizza", "name", pizza.getName(), false, null, null, "Custom error message"));
            return "newPizza";
        }

        return "redirect:/";
    }

}
