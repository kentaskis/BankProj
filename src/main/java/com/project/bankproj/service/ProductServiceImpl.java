package com.project.bankproj.service;

import com.project.bankproj.dto.ProductDto;
import com.project.bankproj.mapper.ProductMapper;
import com.project.bankproj.repository.ProductRepository;
import com.project.bankproj.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getProductsWhereAgreementsQuantityMoreThan(int agreementQuantity) {
        return productMapper.toDtoList(productRepository.findAllProductsWhereAgreementsQuantityMoreThan(agreementQuantity));
    }
}