package com.knkweb.spring5recipesapp.services;

import com.knkweb.spring5recipesapp.commands.RecipeCommand;
import com.knkweb.spring5recipesapp.converters.RecipeCommandToRecipe;
import com.knkweb.spring5recipesapp.converters.RecipeToRecipeCommand;
import com.knkweb.spring5recipesapp.domain.Recipe;
import com.knkweb.spring5recipesapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findByid(Long l) {
        Optional<Recipe> recipe = recipeRepository.findById(l);
        if(recipe.isEmpty()){
            throw new RuntimeException("Recipe not found");
        }
        return recipe.get();
    }

    @Override
    @Transactional
    public RecipeCommand findRecipeCommandById(Long l) {
        Recipe recipe = findByid(l);
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);
        return recipeCommand;
    }

    @Override
    public void deleteById(Long idToDelete) {
        recipeRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findByid(id));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand inRecipeCommand) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(inRecipeCommand);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        RecipeCommand outRecipeCommand = recipeToRecipeCommand.convert(savedRecipe);
        return outRecipeCommand;
    }



}
