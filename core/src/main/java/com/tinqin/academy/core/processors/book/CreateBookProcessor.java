package com.tinqin.academy.core.processors.book;

import com.tinqin.academy.api.operations.createbook.CreateBook;
import com.tinqin.academy.api.operations.createbook.CreateBookInput;
import com.tinqin.academy.api.operations.createbook.CreateBookOutput;
import com.tinqin.academy.persistence.models.Author;
import com.tinqin.academy.persistence.models.Book;
import com.tinqin.academy.persistence.repositories.AuthorRepository;
import com.tinqin.academy.persistence.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CreateBookProcessor implements CreateBook {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public CreateBookOutput process(CreateBookInput input) {
//        Author author = authorRepository
//                .findById(UUID.fromString(input.getAuthor()))
//                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
// test new exception handler
//      .orElseThrow(() -> new BusinessException("Author not found"));

        List<UUID> autorsListOfId = input.getAuthor()
                .stream()
                .map(authorId -> UUID.fromString(authorId))
                .toList()
                ;

        List<Author> authors = authorRepository.findAuthorsById(autorsListOfId);

        Book book = Book
                .builder()
                .title(input.getTitle())
     //           .author(author)
                .author(authors)
                .pages(input.getPages())
                .price(BigDecimal.valueOf(Double.parseDouble(input.getPrice())))
                .build();

        Book persisted = bookRepository.save(book);

        return CreateBookOutput
                .builder()
                .bookId(persisted.getId())
                .build();
    }
}