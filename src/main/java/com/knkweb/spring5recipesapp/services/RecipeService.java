package com.knkweb.spring5recipesapp.services;

import com.knkweb.spring5recipesapp.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findByid(Long l);
}
