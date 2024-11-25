package com.tinqin.academy.rest.controllers;

import com.tinqin.academy.api.APIRoutes;
import com.tinqin.academy.api.hello.HelloWorld;
import com.tinqin.academy.api.hello.HelloWorldInput;
import com.tinqin.academy.api.hello.HelloWorldResult;
import com.tinqin.academy.api.operations.createbook.CreateBook;
import com.tinqin.academy.api.operations.createbook.CreateBookInput;
import com.tinqin.academy.api.operations.createbook.CreateBookOutput;
import com.tinqin.academy.api.operations.getbook.GetBook;
import com.tinqin.academy.api.operations.getbook.GetBookInput;
import com.tinqin.academy.api.operations.getbook.GetBookOutput;
import com.tinqin.academy.api.operations.querybook.QueryBook;
import com.tinqin.academy.api.operations.querybook.QueryBookInput;
import com.tinqin.academy.api.operations.querybook.QueryBookOutput;
import com.tinqin.academy.api.postdemo.PostDemo;
import com.tinqin.academy.api.postdemo.PostDemoInput;
import com.tinqin.academy.api.postdemo.PostDemoResult;
import com.tinqin.academy.api.querydemo.QueryDemo;
import com.tinqin.academy.api.querydemo.QueryDemoInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final GetBook getBook;
    private final QueryBook queryBook;
    //private final HelloWorld helloWorld;
    private final QueryDemo queryDemo;
    private final PostDemo postDemo;
    private final CreateBook createBook;


    @GetMapping(APIRoutes.GET_BOOK)
    public ResponseEntity<?> getBook(@PathVariable("bookId") String bookId) {

        GetBookInput input = GetBookInput
                .builder()
                .bookId(bookId)
                .build();
        try {
            GetBookOutput result = getBook.process(input);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

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

    @PostMapping(APIRoutes.API_BOOK)
    public ResponseEntity<?> createBook(@RequestBody CreateBookInput input) {
        CreateBookOutput result = createBook.process(input);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    //Demo TEST

    @GetMapping
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
    public ResponseEntity<?> postDemo(@RequestBody PostDemoInput postDemoInput) {

        PostDemoResult process = postDemo.process(postDemoInput);
        return ResponseEntity.ok(postDemoInput);
    }

}


