package com.project.bankproj.controller;

import com.project.bankproj.dto.ProductDto;
import com.project.bankproj.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("all-where-agreement-count-more-than/{agreementQuantity}")
    public List<ProductDto> getAllProductsWhereAgreementsQuantityMoreThan(@PathVariable int agreementQuantity) {
        return productService.getProductsWhereAgreementsQuantityMoreThan(agreementQuantity);
    }
}