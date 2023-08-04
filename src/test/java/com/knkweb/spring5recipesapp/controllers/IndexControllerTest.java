package com.knkweb.spring5recipesapp.controllers;

import com.knkweb.spring5recipesapp.domain.Recipe;
import com.knkweb.spring5recipesapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @InjectMocks
    IndexController controller;

    Set<Recipe> recipes;

    @BeforeEach
    void setUp() {
        recipes = new HashSet<>();
        recipes.add(Recipe.builder().id(1L).build());
        recipes.add(Recipe.builder().id(2L).build());
    }

    @Test
    void indexHandler() {
        String view = controller.indexHandler(model);
        assertEquals("index", view);
        verify(recipeService, times(1)).getRecipes();
        verify(model,times(1)).addAttribute(eq("recipes"),anySet());


    }
}