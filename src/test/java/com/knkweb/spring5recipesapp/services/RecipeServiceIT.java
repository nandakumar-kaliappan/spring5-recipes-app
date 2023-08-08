package com.knkweb.spring5recipesapp.services;

import com.knkweb.spring5recipesapp.commands.RecipeCommand;
import com.knkweb.spring5recipesapp.converters.RecipeCommandToRecipe;
import com.knkweb.spring5recipesapp.converters.RecipeToRecipeCommand;
import com.knkweb.spring5recipesapp.domain.Recipe;
import com.knkweb.spring5recipesapp.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test written for verifying updation in Repository using BDD
 */
@SpringBootTest
class RecipeServiceIT {

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Test
    @Transactional
    void saveRecipeCommand() {
//        given
        Recipe oldRecipe = recipeRepository.findAll().iterator().next();
        final String  DESC = "New description";

//        when
        RecipeCommand recipeCommandOld = recipeToRecipeCommand.convert(oldRecipe);
        recipeCommandOld.setDescription(DESC);
        RecipeCommand recipeCommandNew = recipeService.saveRecipeCommand(recipeCommandOld);

//        then
        assertEquals(DESC, recipeCommandNew.getDescription());
        assertEquals(oldRecipe.getId(), recipeCommandNew.getId());
        assertEquals(oldRecipe.getCategories().size(), recipeCommandNew.getCategories().size());
        assertEquals(oldRecipe.getIngredients().size(), recipeCommandNew.getIngredients().size());
    }
}