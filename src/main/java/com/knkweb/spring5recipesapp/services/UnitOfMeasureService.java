package com.knkweb.spring5recipesapp.services;

import com.knkweb.spring5recipesapp.commands.UnitOfMeasureCommand;
import com.knkweb.spring5recipesapp.domain.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
