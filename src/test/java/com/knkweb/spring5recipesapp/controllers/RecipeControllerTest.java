package com.knkweb.spring5recipesapp.controllers;

import com.knkweb.spring5recipesapp.commands.RecipeCommand;
import com.knkweb.spring5recipesapp.domain.Recipe;
import com.knkweb.spring5recipesapp.services.RecipeService;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    @InjectMocks
    RecipeController recipeController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    void showMyId() throws Exception {
        Long id = 4L;
        Recipe recipe = Recipe.builder().id(id).build();
        when(recipeService.findByid(eq(id))).thenReturn(recipe);;
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/"+id+"/show/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/show"))
                .andExpect(MockMvcResultMatchers.model().attribute("recipe",notNullValue()))
                .andExpect(MockMvcResultMatchers.model().attribute("recipe",recipe));
        verify(recipeService, only()).findByid(eq(id));
    }

    @Test
    void newRecipe() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("recipeCommand",
                        Matchers.isA(RecipeCommand.class) ))
                .andExpect(MockMvcResultMatchers.view().name("recipe/recipeform"));
    }

    @Test
    void updateRecipe() throws Exception{
        final Long ID = 2L;
        RecipeCommand recipeCommand = RecipeCommand.builder().id(2L).build();
        when(recipeService.findRecipeCommandById(ID)).thenReturn(recipeCommand);
        ArgumentCaptor<RecipeCommand> argumentCaptor = ArgumentCaptor.forClass(RecipeCommand.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/"+ID+"/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/recipeform"))
                .andExpect(MockMvcResultMatchers.model().attribute("recipeCommand",
                        argumentCaptor.capture() ));
        assertEquals(ID, argumentCaptor.getValue());
    }

    @Test
    void saveOrUpdateForSave() throws Exception{
//        given
        RecipeCommand recipeCommand = RecipeCommand.builder().id(5L).build();
        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

//        when
        mockMvc.perform(MockMvcRequestBuilders.post("/recipe/")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("id","")
                    .param("description", "Some Description"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.view().name(
                        "redirect:/recipe/"+recipeCommand.getId()+"/show/"));
    }



}