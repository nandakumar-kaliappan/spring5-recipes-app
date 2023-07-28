package com.knkweb.spring5recipesapp.bootstrap;

import com.knkweb.spring5recipesapp.domain.*;
import com.knkweb.spring5recipesapp.repositories.CategoryRepository;
import com.knkweb.spring5recipesapp.repositories.RecipeRepository;
import com.knkweb.spring5recipesapp.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public RecipeBootstrap(RecipeRepository recipeRepository,
                           CategoryRepository categoryRepository,
                            UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
     }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {


        //picking UoM from DB
        Optional<UnitOfMeasure> tableSpoonUomOptional =
                unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupUomOptional =
                unitOfMeasureRepository.findByDescription("Cup");

        if(!cupUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();

        //picking Category from DB
        Optional<Category> drinksCategoryOptional = categoryRepository.findByDescription("Drinks");
        if(!drinksCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found Exception");
        }
        Category drinksCategory = drinksCategoryOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription(
                "American");
        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found Exception");
        }
        Category americanCategory = americanCategoryOptional.get();

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription(
                "Mexican");
        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found Exception");
        }
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Creating two new recipe
            //1. Tea

        Recipe tea = new Recipe("Black Tea", 1, 10, 2, "Tea: heat - mix - drink");
        Notes teaNotes = new Notes(tea, "Enjoy the taste of Tea when it is still hot!");
        tea.setNotes(teaNotes);
        Set<Ingredient> teaIngredients = new HashSet<>();
        teaIngredients.add(new Ingredient(tea,"Water",new BigDecimal(2), cupUom));
        teaIngredients.add(new Ingredient(tea,"Suger",new BigDecimal(2), tableSpoonUom));
        teaIngredients.add(new Ingredient(tea,"Tea powder",new BigDecimal(2), tableSpoonUom));

        tea.setIngredients(teaIngredients);
        tea.getCategories().add(drinksCategory);
        tea.getCategories().add(mexicanCategory);

            //2. Coffee
        Recipe coffee = new Recipe("Black Coffee", 1, 10, 2, "Coffee: heat - mix - drink");
        Notes coffeeNotes = new Notes(coffee, "Better to mix some milk with coffee!");
        coffee.setNotes(coffeeNotes);
        Set<Ingredient> coffeeIngredients = new HashSet<>();
        coffeeIngredients.add(new Ingredient(coffee,"Water",new BigDecimal(3), cupUom));
        coffeeIngredients.add(new Ingredient(coffee,"Suger",new BigDecimal(3), tableSpoonUom));
        coffeeIngredients.add(new Ingredient(coffee,"Coffee powder",new BigDecimal(3),
                tableSpoonUom));

        coffee.setIngredients(coffeeIngredients);

        coffee.setIngredients(coffeeIngredients);
        coffee.getCategories().add(drinksCategory);
        coffee.getCategories().add(americanCategory);

        List<Recipe> recipes = new ArrayList<>(2);
        recipes.add(tea);
        recipes.add(coffee);



        return  recipes;




    }


}
