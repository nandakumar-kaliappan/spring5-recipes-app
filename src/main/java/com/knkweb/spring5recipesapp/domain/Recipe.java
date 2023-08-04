package com.knkweb.spring5recipesapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    private Byte[] image;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;



    @ManyToMany
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Recipe(String description,Integer cookTime,Integer prepTime, Integer servings, String directions) {
        this.description = description;
        this.cookTime = cookTime;
        this.prepTime = prepTime;
        this.servings = servings;
        this.directions = directions;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Recipe;
    }


}