package com.tinqin.academy.core.processors.author;

import com.tinqin.academy.api.errors.OperationError;
import com.tinqin.academy.api.operations.createauthor.CreateAuthor;
import com.tinqin.academy.api.operations.createauthor.CreateAuthorInput;
import com.tinqin.academy.api.operations.createauthor.CreateAuthorOutput;
import com.tinqin.academy.core.errorhandler.base.ErrorHandler;
import com.tinqin.academy.persistence.models.Author;
import com.tinqin.academy.persistence.repositories.AuthorRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAuthorProcessor implements CreateAuthor {
    private final AuthorRepository authorRepository;
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;

    @Override
    public Either<OperationError, CreateAuthorOutput> process(CreateAuthorInput input) {
        return Try.of(() -> result(input))
                .toEither()
                .mapLeft(errorHandler::handle);
    }

    //public CreateAuthorOutput process(CreateAuthorInput input) {
    public CreateAuthorOutput result(CreateAuthorInput input) {
        Author author = conversionService.convert(input, Author.class);

        Author persisted = authorRepository.save(author);

        return CreateAuthorOutput
                .builder()
                .authorId(persisted.getId())
                .build();
    }
}