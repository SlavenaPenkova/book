package com.tinqin.academy.api.operations.querybook;

import com.tinqin.academy.api.base.Processor;

public interface QueryBook extends Processor<QueryBookOutput, QueryBookInput> {
    QueryBookOutput process(QueryBookInput input);
}
