package com.tinqin.academy.rest.controllers;

import com.tinqin.academy.api.APIRoutes;
import com.tinqin.academy.api.errors.OperationError;
import com.tinqin.academy.api.operations.createbook.CreateBook;
import com.tinqin.academy.api.operations.createbook.CreateBookInput;
import com.tinqin.academy.api.operations.createbook.CreateBookOutput;
import com.tinqin.academy.api.operations.getbook.GetBook;
import com.tinqin.academy.api.operations.getbook.GetBookInput;
import com.tinqin.academy.api.operations.getbook.GetBookOutput;
//import com.tinqin.academy.api.operations.querybook.QueryBook;
//import com.tinqin.academy.api.operations.querybook.QueryBookInput;
//import com.tinqin.academy.api.operations.querybook.QueryBookOutput;
import com.tinqin.academy.api.operations.getbookbyauthor.GetBookByAuthor;
import com.tinqin.academy.api.operations.getbookbyauthor.GetBookByAuthorInput;
import com.tinqin.academy.api.operations.getbookbyauthor.GetBookByAuthorOutput;
import com.tinqin.academy.api.postdemo.PostDemo;
import com.tinqin.academy.api.postdemo.PostDemoInput;
import com.tinqin.academy.api.postdemo.PostDemoResult;
import com.tinqin.academy.api.querydemo.QueryDemo;
import com.tinqin.academy.api.querydemo.QueryDemoInput;
import com.tinqin.academy.rest.models.LocaleHeader;
import io.swagger.v3.oas.annotations.Operation;
import io.vavr.control.Either;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
//public class BookController {
public class BookController extends BaseController {

    private final GetBook getBook;
    //private final QueryBook queryBook;
    //private final HelloWorld helloWorld;
    private final QueryDemo queryDemo;
    private final PostDemo postDemo;
    private final CreateBook createBook;
    private final GetBookByAuthor getBookByAuthor;
    private final LocaleHeader localeHeader;


    @GetMapping(APIRoutes.GET_BOOK)
    @Operation(  summary = "Get book",
            description = "Get book by UUID and return OK")
    public ResponseEntity<?> getBook(@PathVariable("bookId") String bookId) {
        //@RequestHeader(value = "locale", required = false, defaultValue = "en") String locale) {
        GetBookInput input = GetBookInput
                .builder()
                .bookId(bookId)
                .locale(localeHeader.getLocale())
                .build();

        Either<OperationError,GetBookOutput> result = getBook.process(input);

        return mapToResponseEntity(result,HttpStatus.OK);

//no vavr
//        try {
//            GetBookOutput result = getBook.process(input);
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }

    }
/*
    @GetMapping(APIRoutes.API_BOOK)
    public ResponseEntity<?> getBooks(
            @RequestParam(value = "title", required = false, defaultValue = "") String title,
            @RequestParam(value = "author", required = false, defaultValue = "") String author
    ) {

        QueryBookInput input = QueryBookInput
                .builder()
                .title(title)
                .author(author)
                .build();
        try {
            QueryBookOutput result = queryBook.process(input);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
*/
    @PostMapping(APIRoutes.API_BOOK)
    @Operation(  summary = "Create a book",
            description = "Create a book and return response")
    public ResponseEntity<?> createBook(@Valid @RequestBody CreateBookInput input) {

        Either<OperationError,CreateBookOutput> result =createBook.process(input);

        return mapToResponseEntity(result,HttpStatus.CREATED);

 //no vavr
//        CreateBookOutput result = createBook.process(input);
//
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    //GetBookByAuthor
    @GetMapping(APIRoutes.API_BOOK)
    public ResponseEntity<?> getBooksByAuthor(@RequestParam("authorId") String authorId,
                                              @PageableDefault(page = 0, size = 2) Pageable pageable) {

        GetBookByAuthorInput input = GetBookByAuthorInput
                .builder()
                .authorId(authorId)
                .pageable(pageable)
                .build();

        Either<OperationError, GetBookByAuthorOutput> process = getBookByAuthor.process(input);

        return mapToResponseEntity(process, HttpStatus.OK);
    }

    //Demo TEST

    @GetMapping
    @Operation(  summary = "Query demo",
            description = "Query demo of params")
    public ResponseEntity<?> queryDemo(@RequestParam(value = "myparam", required = false, defaultValue = "Hello") String queryParam,
                                       @RequestParam(value = "param2", required = true) String queryParam2) {

        QueryDemoInput build = QueryDemoInput
                .builder()
                .queryParam(queryParam)
                .queryParam(queryParam2)
                .build();

        queryDemo.process(build);

        return ResponseEntity.ok(queryDemo.process(build));
    }

    @PostMapping
    @Operation(  summary = "Post demo",
            description = "Post demo and return OK")
    public ResponseEntity<?> postDemo(@RequestBody PostDemoInput postDemoInput) {

        Either<OperationError,PostDemoResult> result =postDemo.process(postDemoInput);
        return mapToResponseEntity(result,HttpStatus.OK);
//no vavr
//        PostDemoResult process = postDemo.process(postDemoInput);
//        return ResponseEntity.ok(postDemoInput);
    }

}


