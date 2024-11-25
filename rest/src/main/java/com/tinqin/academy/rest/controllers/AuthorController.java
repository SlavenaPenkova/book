package com.tinqin.academy.rest.controllers;

import com.tinqin.academy.api.operations.createauthor.CreateAuthor;
import com.tinqin.academy.api.operations.createauthor.CreateAuthorInput;
import com.tinqin.academy.api.operations.createauthor.CreateAuthorOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.tinqin.academy.api.APIRoutes.API_AUTHOR;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final CreateAuthor createAuthor;


    @PostMapping(API_AUTHOR)
    public ResponseEntity<?> createAuthor(@RequestBody CreateAuthorInput input) {
        CreateAuthorOutput result = createAuthor.process(input);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}