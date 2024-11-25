package com.tinqin.academy.api.operations.createauthor;

import com.tinqin.academy.api.base.ProcessorInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateAuthorInput implements ProcessorInput {

    @NotBlank
    @Length(max = 100)
    private String firstName;

    @NotBlank
    @Length(max = 100)
    private String lastName;
}