package com.project.bankproj.controller;

import com.project.bankproj.dto.ProductDto;
import com.project.bankproj.service.interfaces.ProductService;
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
@RequestMapping("product")
@RequiredArgsConstructor
@Tag(name = "Product Controller", description = "All activities related to Product model")
public class ProductController {
    private final ProductService productService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("all-where-agreement-count-more-than/{agreementQuantity}")
    @Operation(
            summary = "Query for all products where agreements quantity more than given",
            description = "Getting all products where agreements quantity more than given"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned a list of all products where agreements quantity more than given value",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))
            })
    public List<ProductDto> getAllProductsWhereAgreementsQuantityMoreThan(
            @PathVariable @Parameter(required = true, description = "Quantity of agreement") int agreementQuantity
    ) {
        return productService.getProductsWhereAgreementsQuantityMoreThan(agreementQuantity);
    }
}