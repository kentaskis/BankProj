package com.project.bankproj.controller;

import com.project.bankproj.dto.ProductDto;
import com.project.bankproj.service.interfaces.ProductService;
import com.project.bankproj.util.DtoCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(ProductController.class)
@DisplayName("Test class for ProductController")
class ProductControllerTest {
    @MockBean
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllProductsWhereAgreementsQuantityMoreThan() throws Exception {
        final int AGREEMENT_COUNT = 4;
        final List<ProductDto> productDtoList = new ArrayList<>();
        final ProductDto productDto = DtoCreator.getProductDto();
        productDtoList.add(productDto);

        when(productService.getProductsWhereAgreementsQuantityMoreThan(AGREEMENT_COUNT)).thenReturn(productDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/all-where-agreement-count-more-than/" + AGREEMENT_COUNT))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(productDto.getId())))
                .andExpect(jsonPath("$[0].name", is(productDto.getName())))
                .andExpect(jsonPath("$[0].status", is(productDto.getStatus().toString())))
                .andExpect(jsonPath("$[0].status", is(productDto.getStatus().toString())))
                .andExpect(jsonPath("$[0].currency", is(productDto.getCurrency().toString())))
                .andExpect(jsonPath("$[0].interestRate").value(productDto.getInterestRate().toString()))
                .andExpect(jsonPath("$[0].limit", is(productDto.getLimit())))
        ;
    }
}