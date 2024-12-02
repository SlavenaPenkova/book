package com.tinqin.academy.api.operations.getbook;

import com.tinqin.academy.api.base.Processor;
import com.tinqin.academy.api.base.ProcessorInput;
import com.tinqin.academy.api.base.ProcessorResult;
import com.tinqin.academy.api.errors.OperationError;
import io.vavr.control.Either;

public interface GetBook extends Processor<GetBookOutput, GetBookInput> {
}
