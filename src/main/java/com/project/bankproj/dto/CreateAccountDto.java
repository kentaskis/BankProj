package com.project.bankproj.dto;

import com.project.bankproj.entity.enums.CurrencyType;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class CreateAccountDto {
    String clientId;
    String name;
    BigDecimal balance;
    CurrencyType currency;
}