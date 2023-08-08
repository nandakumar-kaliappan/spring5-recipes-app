package com.knkweb.spring5recipesapp.converters;

import com.knkweb.spring5recipesapp.commands.NotesCommand;
import com.knkweb.spring5recipesapp.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
    @Override
    public NotesCommand convert(Notes source) {
        return null;
    }
}
