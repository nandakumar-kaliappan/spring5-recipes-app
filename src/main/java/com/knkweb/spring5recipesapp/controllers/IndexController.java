package com.knkweb.spring5recipesapp.controllers;

import com.knkweb.spring5recipesapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
        log.debug("IndexController : Instantiation");
    }

    @RequestMapping({"","/","/index"})
    public String indexHandler(Model model){
        log.debug("IndexController : Received Request to indexHandler()");
        model.addAttribute("recipes", recipeService.getRecipes());
        log.debug("IndexController : forwarding to index.html");
        return "index";
    }

}
