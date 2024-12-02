package com.tinqin.academy.core.processors.hello;

import com.tinqin.academy.api.errors.OperationError;
import com.tinqin.academy.api.querydemo.QueryDemoInput;
import com.tinqin.academy.api.querydemo.QueryDemoResult;
import com.tinqin.academy.core.errorhandler.base.ErrorHandler;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class QueryDemo implements com.tinqin.academy.api.querydemo.QueryDemo {
    private  ErrorHandler errorHandler;
    @Override
    public Either<OperationError, QueryDemoResult> process(QueryDemoInput input) {
    //public QueryDemoResult process(QueryDemoInput input) {

        return Try.of(()->QueryDemoResult
                .builder()
                .resultMessage("My query contains the following params: " + input.getQueryParam())
                .build())
                .toEither()
                .mapLeft(errorHandler::handle);
    }
}
