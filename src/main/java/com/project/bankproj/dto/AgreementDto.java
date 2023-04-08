package com.project.bankproj.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.bankproj.entity.enums.AgreementStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Value
public class AgreementDto {

    int id;
    AgreementStatus status;
    BigDecimal interestRate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Timestamp createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Timestamp updatedAt;
    AccountShortDto account;
    ProductDto product;
}