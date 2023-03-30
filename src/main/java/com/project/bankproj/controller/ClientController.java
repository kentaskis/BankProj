package com.project.bankproj.controller;

import com.project.bankproj.dto.ClientDto;
import com.project.bankproj.service.interfaces.ClientService;
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
@RequestMapping("client")
@RequiredArgsConstructor
@Tag(name = "Client Controller", description = "All activities related to Client model")
public class ClientController {
    private final ClientService clientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/where-transaction-more-than/{transactionCount}")
    @Operation(
            summary = "Query for all clients with a given number of transactions",
            description = "Getting all client with transaction count more than given"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned a list of clients whose number of transactions exceeds the given value",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ClientDto.class)))
            })
    public List<ClientDto> getAllClientsWhereTransactionMoreThan(
            @PathVariable @Parameter(required = true, description = "Count of transaction") Integer transactionCount
    ) {
        return clientService.getAllWhereTransactionMoreThan(transactionCount);
    }
}
