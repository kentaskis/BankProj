package com.project.bankproj.service;

import com.project.bankproj.dto.ProductDto;
import com.project.bankproj.entity.Product;
import com.project.bankproj.mapper.ProductMapper;
import com.project.bankproj.repository.ProductRepository;
import com.project.bankproj.util.DtoCreator;
import com.project.bankproj.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Test class for ProductServiceImpl")
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    ProductRepository repository;
    @Mock
    ProductMapper mapper;
    @InjectMocks
    ProductServiceImpl service;

    @Test
    void getProductsWhereAgreementsQuantityMoreThan() {
        final int AGREEMENT_COUNT = 4;
        List<Product> productList = new ArrayList<>();
        productList.add(EntityCreator.getProduct());
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(DtoCreator.getProductDto());

        when(repository.findAllProductsWhereAgreementsQuantityMoreThan(AGREEMENT_COUNT)).thenReturn(productList);
        when(mapper.toDtoList(productList)).thenReturn(productDtoList);

        assertEquals(productDtoList, service.getProductsWhereAgreementsQuantityMoreThan(AGREEMENT_COUNT));
        verify(repository).findAllProductsWhereAgreementsQuantityMoreThan(AGREEMENT_COUNT);
    }
}