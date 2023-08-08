package com.knkweb.spring5recipesapp.converters;

import com.knkweb.spring5recipesapp.commands.CategoryCommand;
import com.knkweb.spring5recipesapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryCommandToCategoryTest {

    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    void testNotNull(){
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void convert() {
        final Long ID = 4L;
        final String DESC = "Some Desc";
        Category category =
                converter.convert(CategoryCommand.builder().id(ID).description(DESC).build());
        assertNotNull(category);
        assertEquals(ID,category.getId());
        assertEquals(DESC, category.getDescription());
    }
}