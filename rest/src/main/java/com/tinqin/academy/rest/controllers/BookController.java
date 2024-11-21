package com.tinqin.academy.rest.controllers;

import com.tinqin.academy.api.APIRoutes;
import com.tinqin.academy.api.hello.HelloWorld;
import com.tinqin.academy.api.hello.HelloWorldInput;
import com.tinqin.academy.api.hello.HelloWorldResult;
import com.tinqin.academy.api.operations.getbook.GetBook;
import com.tinqin.academy.api.operations.getbook.GetBookInput;
import com.tinqin.academy.api.operations.getbook.GetBookOutput;
import com.tinqin.academy.api.postdemo.PostDemo;
import com.tinqin.academy.api.postdemo.PostDemoInput;
import com.tinqin.academy.api.postdemo.PostDemoResult;
import com.tinqin.academy.api.querydemo.QueryDemo;
import com.tinqin.academy.api.querydemo.QueryDemoInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final GetBook getBook;
    private final HelloWorld helloWorld;
    private final QueryDemo queryDemo;
    private final PostDemo postDemo;


    @GetMapping(APIRoutes.GET_BOOK)
    public ResponseEntity<?> getBook(@PathVariable("bookId") String bookId) {

        GetBookInput input = GetBookInput
                .builder()
                .bookId(bookId)
                .build();

        GetBookOutput result = getBook.process(input);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> queryDemo(@RequestParam(value = "myparam", required = false, defaultValue = "Hello") String queryParam,
                                       @RequestParam (value = "param2", required = true) String queryParam2) {

        QueryDemoInput build = QueryDemoInput
                .builder()
                .queryParam(queryParam)
                .queryParam(queryParam2)
                .build();

        queryDemo.process(build);

        return ResponseEntity.ok(queryDemo.process(build));
    }
    @PostMapping
    public ResponseEntity<?> postDemo (@RequestBody PostDemoInput postDemoInput) {

        PostDemoResult process = postDemo.process(postDemoInput);
        return ResponseEntity.ok(postDemoInput);
    }

}
