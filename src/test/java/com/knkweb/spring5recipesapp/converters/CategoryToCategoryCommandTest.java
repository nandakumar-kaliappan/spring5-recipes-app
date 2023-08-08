package com.knkweb.spring5recipesapp.converters;

import com.knkweb.spring5recipesapp.commands.CategoryCommand;
import com.knkweb.spring5recipesapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryToCategoryCommandTest {

    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    void testNotNull(){
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() {
        final Long ID = 4L;
        final String DESC = "Some Desc";
        CategoryCommand result =
                converter.convert(Category.builder().id(ID).description(DESC).build());
        assertNotNull(result);
        assertEquals(ID,result.getId());
        assertEquals(DESC, result.getDescription());
    }

}