package com.tinqin.academy.api.user;

import static com.tinqin.academy.api.ValidationMessages.CLIENT_ID_CANNOT_BE_NULL;
import static com.tinqin.academy.api.ValidationMessages.USER_ID_CANNOT_BE_NULL;

import com.tinqin.academy.api.base.ProcessorInput;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.UUID;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class BlockUserInput implements ProcessorInput {

    @UUID
    @NotBlank(message = USER_ID_CANNOT_BE_NULL)
    private String id;

}