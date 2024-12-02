package com.tinqin.academy.core.processors.book;

import com.tinqin.academy.api.errors.OperationError;
import com.tinqin.academy.api.operations.createbook.CreateBook;
import com.tinqin.academy.api.operations.createbook.CreateBookInput;
import com.tinqin.academy.api.operations.createbook.CreateBookOutput;
import com.tinqin.academy.core.errorhandler.base.ErrorHandler;
import com.tinqin.academy.core.errorhandler.exceptions.BusinessException;
import com.tinqin.academy.persistence.models.Author;
import com.tinqin.academy.persistence.models.Book;
import com.tinqin.academy.persistence.repositories.AuthorRepository;
import com.tinqin.academy.persistence.repositories.BookRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static com.tinqin.academy.api.ValidationMessages.AUTHOR_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class CreateBookProcessor implements CreateBook {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ErrorHandler errorHandler;

    @Override
    public Either<OperationError, CreateBookOutput> process(CreateBookInput input) {
        Book book = createBook(input);
        return Try.of(() -> saveBook(book))
                .toEither()
                .mapLeft(errorHandler::handle);
    }

    private List<UUID> convertAuthorListToUUID(CreateBookInput input) {
        return input.getAuthor()
                .stream()
                .map(authorId -> UUID.fromString(authorId))
                .toList();
    }

    private List<Author> getAutorList(CreateBookInput input) {
        List<UUID> autorsListOfId = convertAuthorListToUUID(input);
        List<Author> authors = authorRepository.findAuthorsById(autorsListOfId);
        if (authors.isEmpty()) {
            throw new BusinessException(AUTHOR_NOT_FOUND);
        } else {
            return authors;
        }
    }

    //Book book = Book
    private Book createBook(CreateBookInput input) {
        List<Author> authors = getAutorList(input);
        return Book.builder()
                .title(input.getTitle())
                .author(authors)
                .pages(input.getPages())
                .price(BigDecimal.valueOf(Double.parseDouble(input.getPrice())))
                .build();
    }

    private CreateBookOutput saveBook(Book book) {
        Book persisted = bookRepository.save(book);

        return CreateBookOutput
                .builder()
                .bookId(persisted.getId())
                .build();
    }

}


//Old behaviour without vavr
//@Override
//public CreateBookOutput process(CreateBookInput input) {
//       Author author = authorRepository
//              .findById(UUID.fromString(input.getAuthor()))
//                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
// test new exception handler
//      .orElseThrow(() -> new BusinessException("Author not found"));
//
//    List<UUID> autorsListOfId = input.getAuthor()
//            .stream()
//            .map(authorId -> UUID.fromString(authorId))
//            .toList()
//            ;
//
//    List<Author> authors = authorRepository.findAuthorsById(autorsListOfId);
//
//    Book book = Book
//            .builder()
//            .title(input.getTitle())
//            //           .author(author)
//            .author(authors)
//            .pages(input.getPages())
//            .price(BigDecimal.valueOf(Double.parseDouble(input.getPrice())))
//            .build();
//
//    Book persisted = bookRepository.save(book);
//
//    return CreateBookOutput
//            .builder()
//            .bookId(persisted.getId())
//            .build();
//}