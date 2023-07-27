package com.knkweb.spring5recipesapp.controllers;

import com.knkweb.spring5recipesapp.domain.Category;
import com.knkweb.spring5recipesapp.domain.UnitOfMeasure;
import com.knkweb.spring5recipesapp.repositories.CategoryRepository;
import com.knkweb.spring5recipesapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","/index"})
    public String indexHandler(){
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        System.out.println("Category id :"+categoryOptional.get().getId());
        System.out.println("uom id :" +unitOfMeasureOptional .get().getId());
        return "index";
    }
}
