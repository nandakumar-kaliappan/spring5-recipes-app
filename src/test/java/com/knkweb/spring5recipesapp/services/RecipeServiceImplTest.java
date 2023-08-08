package com.knkweb.spring5recipesapp.services;

import com.knkweb.spring5recipesapp.domain.Recipe;
import com.knkweb.spring5recipesapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        when(recipeRepository.findById(same(1L))).thenReturn(Optional.of(Recipe.builder().id(1L).build()));
        assertEquals(1L, recipeService.findByid(1L).getId());
        verify(recipeRepository,times(1)).findById(eq(1L));
        verifyNoMoreInteractions(recipeRepository);
    }


}