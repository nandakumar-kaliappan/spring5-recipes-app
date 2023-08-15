package com.knkweb.spring5recipesapp.converters;

import com.knkweb.spring5recipesapp.commands.IngredientCommand;
import com.knkweb.spring5recipesapp.commands.UnitOfMeasureCommand;
import com.knkweb.spring5recipesapp.domain.Ingredient;
import com.knkweb.spring5recipesapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if(source == null){
            return null;
        }

        final UnitOfMeasureCommand unitOfMeasureCommand =
                unitOfMeasureToUnitOfMeasureCommand.convert(source.getUnitOfMeasure());
        final IngredientCommand ingredientCommand =
                IngredientCommand.builder().id(source.getId()).unitOfMeasure(unitOfMeasureCommand)
                        .amount(source.getAmount()).description(source.getDescription()).build();
        if(source.getRecipe() != null) {
            ingredientCommand.setRecipeId(source.getRecipe().getId());
        }
        return ingredientCommand;

    }
}
