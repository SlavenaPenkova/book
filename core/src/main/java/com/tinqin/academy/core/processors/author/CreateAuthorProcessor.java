package com.tinqin.academy.core.processors.author;

import com.tinqin.academy.api.operations.createauthor.CreateAuthor;
import com.tinqin.academy.api.operations.createauthor.CreateAuthorInput;
import com.tinqin.academy.api.operations.createauthor.CreateAuthorOutput;
import com.tinqin.academy.persistence.models.Author;
import com.tinqin.academy.persistence.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAuthorProcessor implements CreateAuthor {
    private final AuthorRepository authorRepository;
    private final ConversionService conversionService;

    @Override
    public CreateAuthorOutput process(CreateAuthorInput input) {
        Author author = conversionService.convert(input, Author.class);

        Author persisted = authorRepository.save(author);

        return CreateAuthorOutput
                .builder()
                .authorId(persisted.getId())
                .build();
    }
}