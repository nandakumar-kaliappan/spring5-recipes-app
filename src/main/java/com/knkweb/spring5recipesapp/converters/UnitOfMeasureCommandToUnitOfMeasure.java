package com.knkweb.spring5recipesapp.converters;

import com.knkweb.spring5recipesapp.commands.UnitOfMeasureCommand;
import com.knkweb.spring5recipesapp.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand,
        UnitOfMeasure> {
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        return null;
    }
}
