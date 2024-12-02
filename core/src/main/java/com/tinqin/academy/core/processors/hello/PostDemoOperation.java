package com.tinqin.academy.core.processors.hello;

import com.tinqin.academy.api.errors.OperationError;
import com.tinqin.academy.api.postdemo.PostDemo;
import com.tinqin.academy.api.postdemo.PostDemoInput;
import com.tinqin.academy.api.postdemo.PostDemoResult;
import com.tinqin.academy.core.errorhandler.base.ErrorHandler;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class PostDemoOperation implements PostDemo {
    private ErrorHandler errorHandler;

    @Override
    public Either<OperationError, PostDemoResult> process(PostDemoInput input){
   // public PostDemoResult process(PostDemoInput input) {
   //   PostDemoResult build = PostDemoResult
        return Try.of(()-> PostDemoResult
                .builder()
//                .resultMessage("My post contains the following message: " + input.getMessage())
                .build())
                .toEither()
                .mapLeft(errorHandler::handle);
    }
}