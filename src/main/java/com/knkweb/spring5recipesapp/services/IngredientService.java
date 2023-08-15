package com.knkweb.spring5recipesapp.services;


import com.knkweb.spring5recipesapp.commands.IngredientCommand;

public interface IngredientService {
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
