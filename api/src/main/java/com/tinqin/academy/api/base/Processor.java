package com.tinqin.academy.api.base;

import com.tinqin.academy.api.errors.OperationError;
import io.vavr.control.Either;

public interface Processor < R extends ProcessorResult, I extends ProcessorInput >{
    //case no vavr
   // R process(I input);

     Either<OperationError, R> process(I input);

}
