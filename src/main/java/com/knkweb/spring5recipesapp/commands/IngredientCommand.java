package com.knkweb.spring5recipesapp.commands;


import com.knkweb.spring5recipesapp.domain.Recipe;
import com.knkweb.spring5recipesapp.domain.UnitOfMeasure;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientCommand {
    private Long id;
    private Recipe recipe;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand unitOfMeasure;
}
