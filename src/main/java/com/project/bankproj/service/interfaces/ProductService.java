package com.project.bankproj.service.interfaces;

import com.project.bankproj.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProductsWhereAgreementsQuantityMoreThan(int agreementQuantity);
}