package com.tinqin.academy.api.operations.getbookbyauthor;

import com.tinqin.academy.api.base.ProcessorInput;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Pageable;

import static com.tinqin.academy.api.ValidationMessages.CLIENT_ID_CANNOT_BE_NULL;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class GetBookByAuthorInput implements ProcessorInput {

    @UUID
    @NotBlank
    private String authorId;

    Pageable pageable;
}
