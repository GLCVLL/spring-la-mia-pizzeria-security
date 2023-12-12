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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private PizzaService pizzaService;
	
    @GetMapping
    public String getAllIngredients(Model model) {
        List<Ingredient> ingredients = ingredientService.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredients";
    }
    
    @GetMapping("/new")
    public String createIngredientForm(Model model) {
        Ingredient ingredient = new Ingredient();
        model.addAttribute("ingredient", ingredient);
        return "newIngredient";
    }
    
    @PostMapping("/new")
    public String createIngredient(@Valid @ModelAttribute Ingredient ingredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
			model.addAttribute("ingredient", ingredient);
			
            return "newIngredient";
        }
        ingredientService.save(ingredient);
        return "redirect:/ingredients";
    }
    
	@PostMapping("/delete/{id}")
	public String deleteIngredient(@PathVariable int id) {
		
		Ingredient ingredient = ingredientService.findById(id);
		
		List<Pizza> ingPizzas = ingredient.getPizzas();
		ingPizzas.forEach(p -> {
			
			p.getIngredients().remove(ingredient);
			pizzaService.save(p);
		});
		
		ingredientService.delete(ingredient);
		
		return "redirect:/ingredients";
	}

}
