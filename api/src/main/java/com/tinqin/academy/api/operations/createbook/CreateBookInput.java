package com.tinqin.academy.api.operations.createbook;

import com.tinqin.academy.api.base.ProcessorInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;

import java.util.List;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateBookInput implements ProcessorInput {

    @NotBlank
    @Length(max = 100)
    private String title;

    @NotBlank
    @Length(max = 100)
    private String pages;

    //@UUID
   // private String author;
    private List<String > author;

    @NotBlank
    @Length(max = 100)
    private String price;

}