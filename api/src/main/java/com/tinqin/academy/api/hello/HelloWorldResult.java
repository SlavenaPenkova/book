package com.tinqin.academy.api.hello;

import com.tinqin.academy.api.base.ProcessorResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class HelloWorldResult implements ProcessorResult{
    private final String message;
}
