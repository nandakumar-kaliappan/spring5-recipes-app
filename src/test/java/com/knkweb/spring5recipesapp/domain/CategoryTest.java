package com.knkweb.spring5recipesapp.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @Test
    void getId() {
        Long idValue = 4L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    void getDescription() {
        String description = "TestDescription";
        category.setDescription(description);
        assertEquals(description, category.getDescription());
    }

    @Test
    void getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(Recipe.builder().id(1L).build());
        recipes.add(Recipe.builder().id(2L).build());
        category.setRecipes(recipes);

        assertEquals(2,category.getRecipes().size());
    }
}