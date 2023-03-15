package com.project.bankproj.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class CreateManagerDto {
    @NotEmpty
    @Size(min = 2,max = 50)
    String firstName;

    @NotEmpty
    @Size(min = 2,max = 50)
    String lastName;
}