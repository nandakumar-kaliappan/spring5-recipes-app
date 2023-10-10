package com.knkweb.spring5recipesapp.commands;

import com.knkweb.spring5recipesapp.domain.Difficulty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private Byte[] image;
    private String directions;
    @Builder.Default
        private Set<IngredientCommand> ingredients = new HashSet<>();
    private Difficulty difficulty;
    private NotesCommand notes;
    @Builder.Default
    private Set<CategoryCommand> categories = new HashSet<>();
}
