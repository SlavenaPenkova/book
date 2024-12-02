package com.tinqin.academy.core.processors.book;

import com.tinqin.academy.api.base.ProcessorInput;
import com.tinqin.academy.api.errors.OperationError;
import com.tinqin.academy.api.operations.getbook.GetBook;
import com.tinqin.academy.api.operations.getbook.GetBookInput;
import com.tinqin.academy.api.operations.getbook.GetBookOutput;
import com.tinqin.academy.core.errorhandler.base.ErrorHandler;
import com.tinqin.academy.core.errorhandler.exceptions.BusinessException;
import com.tinqin.academy.persistence.models.Book;
import com.tinqin.academy.persistence.repositories.BookRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.UUID;

import static com.tinqin.academy.api.ValidationMessages.BOOK_NOT_FOUND;

//@Slf4j
@Service
@RequiredArgsConstructor
public class GetBookProcessor implements GetBook {


    private final BookRepository bookRepository;
    private final ErrorHandler errorHandler;

//    @Override
//    public GetBookOutput process(GetBookInput input) {
//       // log.info("Processing GetBookInput {}", input);
//        Book book = fetchBook(input);
//        return convertGetBookInputToGetBookOutput(book);
//    }

    @Override
    public Either<OperationError, GetBookOutput> process(GetBookInput input) {
        return fetchBook(input)
                .map(this::convertGetBookInputToGetBookOutput)
                .toEither()
                .mapLeft(errorHandler::handle);
    }


    private Try<Book> fetchBook(GetBookInput input) {
        return Try.of(()->
                bookRepository.findById(UUID.fromString(input.getBookId()))
                .orElseThrow(()-> new BusinessException(BOOK_NOT_FOUND)));

    }

    private GetBookOutput convertGetBookInputToGetBookOutput(Book book) {
        return GetBookOutput.builder()
                .author(String.valueOf(book.getAuthor()))
                .title(book.getTitle())
                .pages(book.getPages())
                .build();
    }

}
