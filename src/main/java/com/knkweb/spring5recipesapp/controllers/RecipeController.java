package com.knkweb.spring5recipesapp.controllers;

import com.knkweb.spring5recipesapp.commands.RecipeCommand;
import com.knkweb.spring5recipesapp.domain.Recipe;
import com.knkweb.spring5recipesapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show/")
    public String showMyId(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findByid(Long.valueOf(id)));
        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipeCommand", new RecipeCommand());
        return "recipe/recipeform";
    }

    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(Long.valueOf(id));
        model.addAttribute("recipeCommand", recipeCommand);
        return "recipe/recipeform";
    }

    @PostMapping("/recipe/")
    public String saveOrUpdate(@ModelAttribute RecipeCommand inRecipeCommand){
        RecipeCommand outRecipeCommand = recipeService.saveRecipeCommand(inRecipeCommand);
        return "redirect:/recipe/"+outRecipeCommand.getId()+"/show/";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/delete/")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting id: " + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}
