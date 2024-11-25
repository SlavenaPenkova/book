package com.tinqin.academy.api.operations.createbook;


import com.tinqin.academy.api.base.ProcessorResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class CreateBookOutput implements ProcessorResult {

    private UUID bookId;

}

