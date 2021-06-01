package com.learn.recipe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learn.exception.RecipeNotFoundException;
import com.learn.recipe.model.Recipe;
import com.learn.recipe.model.RecipeRepository;

@RestController
@RequestMapping(value = "/api/recipes", produces = "application/json")
public class RecipeController {
	
	@Autowired
	RecipeRepository recipeRepository;
	
	/*
	 * RecipeController(RecipeRepository recipeRepository) { this.recipeRepository =
	 * recipeRepository; }
	 */

	
	@RequestMapping(value = "/", produces = "application/json", method = { RequestMethod.GET })
	public List<Recipe> getRecipes() {
		return recipeRepository.findAll();
	}

	@GetMapping("/{id}")
	public Recipe getRecipes(@PathVariable Long id) {

		return recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id));

	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Recipe createRecipe(@RequestBody Recipe recipe) {

		recipeRepository.save(recipe);

		return recipe;

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Recipe updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {

		recipeRepository.findById(id).map(existingrecipe -> {
			existingrecipe.setName(recipe.getName());
			return recipeRepository.save(existingrecipe);
		}).orElseThrow(() -> new RecipeNotFoundException(id));

		return recipe;

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteRecipe(@PathVariable Long id) {
		recipeRepository.deleteById(id);
	}

}
