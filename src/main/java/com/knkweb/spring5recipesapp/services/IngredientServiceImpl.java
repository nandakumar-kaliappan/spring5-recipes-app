package com.knkweb.spring5recipesapp.services;

import com.knkweb.spring5recipesapp.commands.IngredientCommand;
import com.knkweb.spring5recipesapp.converters.IngredientToIngredientCommand;
import com.knkweb.spring5recipesapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.knkweb.spring5recipesapp.domain.Recipe;
import com.knkweb.spring5recipesapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private  final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if(!recipeOptional.isPresent()){
            //TODO impl error handling
            log.error("recipe id not found . ID "+recipeId);
        }


        Optional<IngredientCommand> ingredientCommandOptional =
                recipeOptional.get().getIngredients().stream().
                        filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert).findFirst();

        if(!recipeOptional.isPresent()){
            //TODO impl error handling
            log.error("ingredient id : "+ ingredientId+" is not found for Recipe Id :"+recipeId);
        }
        return ingredientCommandOptional.get();
    }
}
