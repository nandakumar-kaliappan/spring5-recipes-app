package com.knkweb.spring5recipesapp.converters;

import com.knkweb.spring5recipesapp.commands.UnitOfMeasureCommand;
import com.knkweb.spring5recipesapp.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;

public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure,
        UnitOfMeasureCommand> {
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        return null;
    }
}
