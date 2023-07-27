package com.knkweb.spring5recipesapp.repositories;

import com.knkweb.spring5recipesapp.domain.Category;
import com.knkweb.spring5recipesapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
    Optional<UnitOfMeasure> findByDescription(String unitOfMeasure);

}
