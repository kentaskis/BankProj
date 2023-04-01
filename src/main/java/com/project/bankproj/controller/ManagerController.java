package com.project.bankproj.controller;

import com.project.bankproj.dto.CreateManagerDto;
import com.project.bankproj.dto.ManagerDto;
import com.project.bankproj.service.interfaces.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("manager")
@Tag(name = "Manager Controller", description = "All activities related to Manager model")
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Request for creation new manager", description = "Creating new manager")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned a new manager data",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ManagerDto.class))
            })
    public ManagerDto createManager(@Valid @RequestBody CreateManagerDto createManagerDto) {
        return managerService.create(createManagerDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/contains-client")
    @Operation(
            summary = "Query for all managers which contains client",
            description = "Getting  all managers which contains client"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned a list of managers which contains client",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ManagerDto.class)))
            })
    public List<ManagerDto> getWhichContainsClient() {
        return managerService.getWhichContainsClient();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{managerId}")
    @Operation(
            summary = "Delete manager by id",
            description = "Deleting manager by id"
    )
    public void deleteManager(@Valid @PathVariable int managerId) {
        managerService.delete(managerId);
    }
}