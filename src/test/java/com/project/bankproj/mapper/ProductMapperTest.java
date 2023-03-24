package com.project.bankproj.mapper;

import com.project.bankproj.dto.ProductDto;
import com.project.bankproj.entity.Product;
import com.project.bankproj.util.EntityCreator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductMapperTest {

    private final ProductMapper productMapper = new ProductMapperImpl();

    @Test
    void toDto() {
        List<Product> productList = new ArrayList<>();
        productList.add(EntityCreator.getProduct());
        List<ProductDto> productDtoList = productMapper.toDtoList(productList);

        compareProductListWithListDto(productList, productDtoList);
    }

    private void compareProductEntityWithDto(Product product, ProductDto dto) {
        assertAll(
                () -> assertEquals(product.getId(), dto.getId()),
                () -> assertEquals(product.getStatus(), dto.getStatus()),
                () -> assertEquals(product.getName(), dto.getName()),
                () -> assertEquals(product.getCurrency(), dto.getCurrency()),
                () -> assertEquals(product.getInterestRate(), dto.getInterestRate()),
                () -> assertEquals(product.getLimit(), dto.getLimit()),
                () -> assertEquals(product.getCreatedAt(), dto.getCreatedAt()),
                () -> assertEquals(product.getUpdatedAt(), dto.getUpdatedAt())
        );
    }

    private void compareProductListWithListDto(List<Product> productList, List<ProductDto> productDtoList) {
        assertEquals(productList.size(), productDtoList.size());
        for (int s = 0; s < productList.size(); s++) {
            compareProductEntityWithDto(productList.get(s), productDtoList.get(s));
        }
    }
}