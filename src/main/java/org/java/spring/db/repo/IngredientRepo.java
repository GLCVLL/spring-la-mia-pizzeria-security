package org.java.spring.db.repo;

import java.util.List;

import org.java.spring.db.pojo.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IngredientRepo extends JpaRepository<Ingredient , Integer> {

	List<Ingredient> findByName(String name);
}
