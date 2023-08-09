package com.knkweb.spring5recipesapp.services;

import com.knkweb.spring5recipesapp.commands.RecipeCommand;
import com.knkweb.spring5recipesapp.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findByid(Long l);
    RecipeCommand saveRecipeCommand(RecipeCommand inRecipeCommand);

    RecipeCommand findRecipeCommandById(Long l);

    void deleteById(Long idToDelete);
}
