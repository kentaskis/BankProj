package com.project.bankproj.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
@Schema(description = "Data for creation manager")
public class CreateManagerDto {
    @NotEmpty
    @Size(min = 2,max = 50)
    @Schema(description = "Manager first name", example = "Max")
    String firstName;

    @NotEmpty
    @Size(min = 2,max = 50)
    @Schema(description = "Manager last name", example = "Richard")
    String lastName;
}