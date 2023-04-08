package com.project.bankproj.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.bankproj.entity.enums.CurrencyType;
import com.project.bankproj.entity.enums.ProductStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Value
public class ProductDto {
    int id;
    String name;
    ProductStatus status;
    CurrencyType currency;
    BigDecimal interestRate;
    int limit;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Timestamp createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Timestamp updatedAt;
    ManagerShortDto manager;
}