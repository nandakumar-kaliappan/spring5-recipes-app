package com.knkweb.spring5recipesapp.services;

import com.knkweb.spring5recipesapp.commands.RecipeCommand;
import com.knkweb.spring5recipesapp.converters.RecipeCommandToRecipe;
import com.knkweb.spring5recipesapp.converters.RecipeToRecipeCommand;
import com.knkweb.spring5recipesapp.domain.Recipe;
import com.knkweb.spring5recipesapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @InjectMocks
    RecipeServiceImpl recipeService;

    Set<Recipe> recipes;

    @BeforeEach
    void setUp(){
        recipes = new HashSet<>();
        recipes.add(Recipe.builder().id(1L).build());
        recipes.add(Recipe.builder().id(2L).build());
    }

    @Test
    void getRecipes() {

        when(recipeRepository.findAll()).thenReturn(recipes);

        assertEquals(2,recipeService.getRecipes().size());

        verify(recipeRepository,times(1)).findAll();
    }

    @Test
    void findByid(){

        //        given
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        Long ID = 1L;
        when(recipeRepository.findById(same(ID))).thenReturn(Optional.of(Recipe.builder().id(ID).build()));

//        when
        Recipe recipe = recipeService.findByid(ID);

//        then
        assertEquals(ID, recipe.getId());
        verify(recipeRepository,times(1)).findById(eq(ID));
        verifyNoMoreInteractions(recipeRepository);
    }

    @Test
    void findRecipeCommandById(){
//        given
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        Long ID = 1L;
        Recipe recipe = Recipe.builder().id(ID).build();
        when(recipeRepository.findById(same(ID))).thenReturn(Optional.of(recipe));
        when(recipeToRecipeCommand.convert(any())).thenReturn(RecipeCommand.builder().id(ID).build());

//        when
        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(ID);

//        then
        assertNotNull(recipeCommand);
        assertEquals(ID, recipeCommand.getId());
        InOrder order = inOrder(recipeToRecipeCommand, recipeRepository);
        order.verify(recipeRepository,times(1)).findById(eq(ID));
        order.verify(recipeToRecipeCommand,times(1)).convert(recipe);
        verifyNoMoreInteractions(recipeRepository);
        verifyNoMoreInteractions(recipeToRecipeCommand);
    }


}