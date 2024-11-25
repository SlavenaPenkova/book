package com.tinqin.academy.core.converters;

import com.tinqin.academy.api.operations.createauthor.CreateAuthorInput;
import com.tinqin.academy.persistence.models.Author;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateAuthorInputToAuthor implements Converter<CreateAuthorInput, Author> {

    @Override
    public Author convert(CreateAuthorInput source) {
        return Author
                .builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .build();
    }
}
