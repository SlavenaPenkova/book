package com.tinqin.academy.api.operations.querybook;

import com.tinqin.academy.api.base.ProcessorResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryBookOutput implements ProcessorResult {
    private String author;
    private String title;
    private String pages;
}
