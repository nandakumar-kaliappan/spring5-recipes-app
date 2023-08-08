package com.knkweb.spring5recipesapp.converters;

import com.knkweb.spring5recipesapp.commands.NotesCommand;
import com.knkweb.spring5recipesapp.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Override
    public Notes convert(NotesCommand source) {
        return null;
    }
}
