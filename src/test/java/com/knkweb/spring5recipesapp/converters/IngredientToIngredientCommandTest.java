package com.knkweb.spring5recipesapp.converters;

import com.knkweb.spring5recipesapp.commands.CategoryCommand;
import com.knkweb.spring5recipesapp.commands.IngredientCommand;
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
class IngredientToIngredientCommandTest {

    IngredientToIngredientCommand converter;
    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = 1L;
    public static final Long UOM_ID = 2L;


    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    void testNotNull(){
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void convertWithoutUoM() {
//        given
        Ingredient ingredient = Ingredient.builder().id(ID_VALUE).description(DESCRIPTION).
                amount(AMOUNT).recipe(RECIPE).unitOfMeasure(null).build();
//        when
        IngredientCommand ingredientCommand =converter.convert(ingredient);

//        then
        assertNotNull(ingredientCommand);
        assertNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
    }

    @Test
    void convertWithUoM() {
//        given
        UnitOfMeasure unitOfMeasure= UnitOfMeasure.builder().id(UOM_ID).build();
        Ingredient ingredient = Ingredient.builder().id(ID_VALUE).description(DESCRIPTION).
                amount(AMOUNT).recipe(RECIPE).unitOfMeasure(unitOfMeasure).build();
//        when
        IngredientCommand ingredientCommand =converter.convert(ingredient);

//        then
        assertNotNull(ingredientCommand);
        assertNotNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(UOM_ID, ingredientCommand.getUnitOfMeasure().getId());
    }
}