package com.project.bankproj.controller;

import com.project.bankproj.dto.AccountDto;
import com.project.bankproj.service.interfaces.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("account")
@RequiredArgsConstructor
@Tag(name = "Account Controller", description = "All activities related to Account model")
public class AccountController {

    private final AccountService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Request for all accounts", description = "Getting all accounts")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned list of accounts",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AccountDto.class)))
            })
    public List<AccountDto> listAccounts() {
        return service.getList();
    }

    @GetMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Request account by UUID", description = "Getting account by UUID")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned account",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AccountDto.class))
            })
    public AccountDto get(@PathVariable @Valid @UUID @Parameter(required = true, description = "Account UUID") String uuid) {
        return service.getById(uuid);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/status/{status}")
    @Operation(summary = "Request for all accounts ny status", description = "Getting all accounts by status")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned list of accounts with given status",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AccountDto.class)))
            })
    public List<AccountDto> listAccountsByStatus(
            @PathVariable @Parameter(required = true, description = "Account Status") String status
    ) {
        return service.getListByStatus(status);
    }
}