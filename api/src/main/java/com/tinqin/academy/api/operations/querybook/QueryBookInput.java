package com.tinqin.academy.api.operations.querybook;

import com.tinqin.academy.api.base.ProcessorInput;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.UUID;

import static com.tinqin.academy.api.ValidationMessages.BOOK_AUTHOR_CANNOT_BE_NULL;
import static com.tinqin.academy.api.ValidationMessages.BOOK_TITLE_CANNOT_BE_NULL;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class QueryBookInput implements ProcessorInput {

    @UUID
    @NotBlank(message = BOOK_TITLE_CANNOT_BE_NULL)
    private String title;

    @NotBlank(message = BOOK_AUTHOR_CANNOT_BE_NULL)
    private String author;
}
