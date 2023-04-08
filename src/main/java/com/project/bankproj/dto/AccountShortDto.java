package com.project.bankproj.dto;

import com.project.bankproj.entity.enums.AccountStatus;
import com.project.bankproj.entity.enums.AccountType;
import com.project.bankproj.entity.enums.CurrencyType;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class AccountShortDto {
    String id;
    String name;
    AccountType type;
    AccountStatus status;
    BigDecimal balance;
    CurrencyType currency;
}