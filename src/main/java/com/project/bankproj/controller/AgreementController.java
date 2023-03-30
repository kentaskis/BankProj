package com.project.bankproj.controller;

import com.project.bankproj.dto.AgreementDto;
import com.project.bankproj.service.interfaces.AgreementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agreement")
@RequiredArgsConstructor
@Tag(name = "Agreement Controller", description = "All activities related to Agreement model")
public class AgreementController {

    private final AgreementService agreementService;

    @GetMapping("by-manager-id/{managerId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Request for all agreements by manager id",
            description = "Getting all agreements by manager id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned list of agreements by manager id",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AgreementDto.class)))
            })
    public List<AgreementDto> getAgreementsWhereManagerIdIs(
            @PathVariable @Parameter(required = true, description = "Manager Id") int managerId
    ) {
        return agreementService.getAgreementsByManagerId(managerId);
    }
}