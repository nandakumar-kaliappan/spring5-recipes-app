package com.knkweb.spring5recipesapp.converters;

import com.knkweb.spring5recipesapp.commands.IngredientCommand;
import com.knkweb.spring5recipesapp.commands.UnitOfMeasureCommand;
import com.knkweb.spring5recipesapp.domain.Category;
import com.knkweb.spring5recipesapp.domain.Ingredient;
import com.knkweb.spring5recipesapp.domain.Recipe;
import com.knkweb.spring5recipesapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IngredientCommandToIngredientTest {

    IngredientCommandToIngredient converter;
    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = 1L;
    public static final Long UOM_ID = 2L;



    @BeforeEach
    void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    void testNotNull(){
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convertWithoutUoM() {
//        given
        IngredientCommand ingredientCommand = IngredientCommand.builder().id(ID_VALUE).description(DESCRIPTION).
                amount(AMOUNT).recipe(RECIPE).unitOfMeasure(null).build();
//        when
        Ingredient ingredient =converter.convert(ingredientCommand);

//        then
        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
    }

    @Test
    void convertWithUoM() {
//        given
        UnitOfMeasureCommand unitOfMeasure= UnitOfMeasureCommand.builder().id(UOM_ID).build();
        IngredientCommand ingredientCommand = IngredientCommand.builder().id(ID_VALUE).description(DESCRIPTION).
                amount(AMOUNT).recipe(RECIPE).unitOfMeasure(unitOfMeasure).build();
//        when
        Ingredient ingredient =converter.convert(ingredientCommand);

//        then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());

    }
}