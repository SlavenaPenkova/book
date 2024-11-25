package com.tinqin.academy.api.operations.createauthor;


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
public class CreateAuthorOutput implements ProcessorResult {

    private UUID authorId;

}

