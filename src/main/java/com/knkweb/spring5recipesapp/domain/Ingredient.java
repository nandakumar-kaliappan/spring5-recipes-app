package com.knkweb.spring5recipesapp.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Builder
@AllArgsConstructor
@ToString
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Recipe recipe;
    private String description;
    private BigDecimal amount;
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    public Ingredient() {
    }

    public Ingredient(Recipe recipe, String description, BigDecimal amount,
                      UnitOfMeasure unitOfMeasure) {
        this.recipe = recipe;
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Ingredient;
    }


}
