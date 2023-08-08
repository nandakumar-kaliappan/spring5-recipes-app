package com.knkweb.spring5recipesapp.converters;

import com.knkweb.spring5recipesapp.commands.CategoryCommand;
import com.knkweb.spring5recipesapp.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {
    @Override
    public CategoryCommand convert(Category source) {
        if(source == null){
            return null;
        }
        final CategoryCommand categoryCommand =
                CategoryCommand.builder().description(source.getDescription()).id(source.getId()).build();
        return  categoryCommand;
    }
}
