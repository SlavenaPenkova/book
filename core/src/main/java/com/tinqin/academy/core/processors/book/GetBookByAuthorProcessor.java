package com.tinqin.academy.core.processors.book;

import com.tinqin.academy.api.errors.OperationError;
import com.tinqin.academy.api.models.BookModel;
import com.tinqin.academy.api.operations.getbookbyauthor.GetBookByAuthor;
import com.tinqin.academy.api.operations.getbookbyauthor.GetBookByAuthorInput;
import com.tinqin.academy.api.operations.getbookbyauthor.GetBookByAuthorOutput;
import com.tinqin.academy.core.errorhandler.base.ErrorHandler;
import com.tinqin.academy.core.errorhandler.exceptions.BusinessException;
import com.tinqin.academy.persistence.models.Author;
import com.tinqin.academy.persistence.models.Book;
import com.tinqin.academy.persistence.repositories.AuthorRepository;
import com.tinqin.academy.persistence.repositories.BookRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GetBookByAuthorProcessor implements GetBookByAuthor {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;

    @Override
    public Either<OperationError, GetBookByAuthorOutput> process(GetBookByAuthorInput input) {
        return Try.of(() -> {

                    Author author = authorRepository
                            .findById(UUID.fromString(input.getAuthorId()))
                            .orElseThrow(() -> new BusinessException("Author not found"));

                    List<Book> pagedBooks = bookRepository
                            .findByAuthor(author, input.getPageable());

                    List<BookModel> bookModels = pagedBooks
                            .stream()
                            .map(bookEntity -> conversionService.convert(bookEntity, BookModel.class))
                            .toList();
                    PageRequest.of(0,2, (Sort)Sort.by(Sort.Order.desc("title")).and(Sort.Order.desc("createdAt")));
                    return GetBookByAuthorOutput.builder()
                            .books(bookModels)
                            .build();
                })
                .toEither()
                .mapLeft(errorHandler::handle);
    }
}