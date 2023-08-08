package com.knkweb.spring5recipesapp.converters;

import com.knkweb.spring5recipesapp.commands.RecipeCommand;
import com.knkweb.spring5recipesapp.domain.Category;
import com.knkweb.spring5recipesapp.domain.Ingredient;
import com.knkweb.spring5recipesapp.domain.Notes;
import com.knkweb.spring5recipesapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final NotesCommandToNotes notesCommandToNotes;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final CategoryCommandToCategory categoryCommandToCategory;

    public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 CategoryCommandToCategory categoryCommandToCategory) {
        this.notesCommandToNotes = notesCommandToNotes;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.categoryCommandToCategory = categoryCommandToCategory;
    }


    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if(source == null){
            return null;
        }
        Notes notes = notesCommandToNotes.convert(source.getNotes());


        Recipe recipe = Recipe.builder()
                .id(source.getId())
                .description(source.getDescription())
                .directions(source.getDirections())
                .prepTime(source.getPrepTime())
                .cookTime(source.getCookTime())
                .servings(source.getServings())
                .source(source.getSource())
                .url(source.getUrl())
                .difficulty(source.getDifficulty())
                .notes(notes)
                .build();
        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            Set<Ingredient> ingredients = new HashSet<>();
            source.getIngredients().stream().forEach(ingredientCommand ->
                    ingredients.add(ingredientCommandToIngredient.convert(ingredientCommand)));
            recipe.setIngredients(ingredients);
        }


        if (source.getCategories() != null && source.getCategories().size() > 0) {
            Set<Category> categories = new HashSet<>();
            source.getCategories().stream().forEach(categoryCommand ->
                    categories.add(categoryCommandToCategory.convert(categoryCommand)));
            recipe.setCategories(categories);
        }
        return recipe;


    }
}
