package com.knkweb.spring5recipesapp.services;

import com.knkweb.spring5recipesapp.commands.IngredientCommand;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long valueOf, Long valueOf1) {
        return null;
    }
}
