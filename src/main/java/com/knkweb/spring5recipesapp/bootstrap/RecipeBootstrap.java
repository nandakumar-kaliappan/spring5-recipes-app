package com.knkweb.spring5recipesapp.bootstrap;

import com.knkweb.spring5recipesapp.domain.*;
import com.knkweb.spring5recipesapp.repositories.CategoryRepository;
import com.knkweb.spring5recipesapp.repositories.RecipeRepository;
import com.knkweb.spring5recipesapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
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
        log.debug("Bootstrap : Instantiated");
     }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Bootstrap : Bootstrap called");
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
        log.debug("Bootstrap : UoMs fetched");

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

        log.debug("Bootstrap : Category fetched");

        List<Recipe> recipes = new ArrayList<>(2);

        //Creating two new recipe
            //1. Tea

        String teaDirection = "Boil water in a saucepan.\n" +
                "Add sugar and tea powder in it and boil it for 3-4 minutes on medium flame.\n" +
                "Add milk and boil it over medium flame for 6-7 minutes or until bubble starts to" +
                " rise. You will see the change in color of the tea from milky shade to brown " +
                "shade when it is ready.\n" +
                "Turn off the gas and strain tea in cups.";

        Recipe tea = Recipe.builder()
                .description("Milk Tea")
                .prepTime(2)
                .cookTime(5)
                .servings(3)
                .url("https://foodviva.com/tea-recipes/milk-tea-recipe/")
                .source("2")
                .difficulty(Difficulty.EASY)
                .directions(teaDirection)
                .build();
        Notes teaNotes = new Notes(tea, "It is one of the most popular hot beverages in world and especially in India. A cup of milk tea (chai) in morning gives refreshing feel and put you on track of long hard day. It can be prepared with milk or milk powder and various types of plain or flavored tea powders. This recipe prepares Indian tea using milk, sugar, tea powder and water.");
//        Recipe tea = new Recipe("Black Tea", 1, 10, 2,
//                "Tea: heat - mix - drink");
//        Notes teaNotes = new Notes(tea, "Enjoy the taste of Tea when it is still hot!");
        tea.setNotes(teaNotes);

        Set<Ingredient> teaIngredients = new HashSet<>();
        teaIngredients.add(new Ingredient(tea,"Water",new BigDecimal(2), cupUom));
        teaIngredients.add(new Ingredient(tea,"Milk",new BigDecimal(1), cupUom));
        teaIngredients.add(new Ingredient(tea,"Suger",new BigDecimal(3), tableSpoonUom));
        teaIngredients.add(new Ingredient(tea,"Tea powder",new BigDecimal(2), tableSpoonUom));
        tea.setIngredients(teaIngredients);

        tea.getCategories().add(drinksCategory);
        tea.getCategories().add(mexicanCategory);

        recipes.add(tea);

            //2. Coffee
        String coffeeDirection = "Take 1 tablespoon instant coffee powder in the jar of a blender" +
                " or a mixer grinder.\n" +
                "step-2\n" +
                "Add 2 tablespoons warm water.\n" +
                "step-3\n" +
                "Add 3-4 tablespoons sugar (or to taste).\n" +
                "step-4\n" +
                "Add 5-6 ice cubes.\n" +
                "\n" +
                "step-5\n" +
                "Pour 2 cups chilled milk.\n" +
                "step-6\n" +
                "Close the jar with a lid and blend it until the mixture turns frothy. Remove the" +
                " lid and pour prepared iced coffee into a serving glass and serve immediately. " +
                "Enjoy the Cold Coffee in the afternoon during hot summer days";

        Recipe coffee = Recipe.builder()
                .description("Cold Coffee")
                .prepTime(5)
                .cookTime(1)
                .servings(2)
                .url("https://foodviva.com/summer-recipes/cold-coffee/")
                .source("2")
                .difficulty(Difficulty.MODERATE)
                .directions(coffeeDirection)
                .build();
//        Notes coffeeNotes = new Notes(coffee, "It is one of the most popular hot beverages in
//        world and
//        especially in India. A cup of milk coffee (chai) in morning gives refreshing feel and put
//        you on track of long hard day. It can be prepared with milk or milk powder and various
//        types of plain or flavored coffee powders. This recipe prepares Indian coffee using milk,
//        sugar,
//        coffee powder and water.");
//        Recipe coffee = new Recipe("Black Coffee", 1, 10, 2,
//                "Coffee: heat - mix - drink");
        Notes coffeeNotes = new Notes(coffee, "Cold Coffee, a creamy coffee flavored milk based chilled beverage is perfect to serve as afternoon drink during hot summer days. Just blend instant coffee powder (or brewed coffee), sugar, ice cubes and milk together in a blender and your iced cold coffee is ready to enjoy. This cold coffee is made without vanilla ice cream however, you can add vanilla ice cream to make it thick and creamier.");
        coffee.setNotes(coffeeNotes);

        Set<Ingredient> coffeeIngredients = new HashSet<>();
        coffeeIngredients.add(new Ingredient(coffee,"warm Water",new BigDecimal(2), tableSpoonUom));
        coffeeIngredients.add(new Ingredient(coffee,"chilled Milk",new BigDecimal(2), cupUom));
        coffeeIngredients.add(new Ingredient(coffee,"Ice Cube",new BigDecimal(1), cupUom));
        coffeeIngredients.add(new Ingredient(coffee,"Suger",new BigDecimal(3), tableSpoonUom));
        coffeeIngredients.add(new Ingredient(coffee,"Instant Coffee Powder",new BigDecimal(1),
                tableSpoonUom));
        coffee.setIngredients(coffeeIngredients);

        coffee.getCategories().add(drinksCategory);
        coffee.getCategories().add(americanCategory);



        recipes.add(coffee);
        log.debug("Bootstrap : Recipes loaded - end");



        return  recipes;




    }


}
