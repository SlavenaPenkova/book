package com.tinqin.academy.core.processors.hello;

import com.tinqin.academy.api.errors.OperationError;
import com.tinqin.academy.api.hello.HelloWorld;
import com.tinqin.academy.api.hello.HelloWorldInput;
import com.tinqin.academy.api.hello.HelloWorldResult;
import com.tinqin.academy.core.errorhandler.base.ErrorHandler;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;


@Service
public class HelloWorldOperation implements HelloWorld {

    private  ErrorHandler errorHandler;

    // public HelloWorldResult process(HelloWorldInput input) {
   @Override
    public Either<OperationError, HelloWorldResult> process(HelloWorldInput input) {

        return Try.of(()-> HelloWorldResult
                .builder()
                .message("Hello from processor")
                .build())
                .toEither()
                .mapLeft(errorHandler::handle);
    }
}
