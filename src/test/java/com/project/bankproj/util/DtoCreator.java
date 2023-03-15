package com.project.bankproj.util;


import com.project.bankproj.dto.AccountDto;
import com.project.bankproj.dto.ClientDto;
import com.project.bankproj.dto.ManagerClientDto;
import com.project.bankproj.dto.ManagerDto;
import com.project.bankproj.entity.enums.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DtoCreator {

    public static ManagerDto getManagerDto() {
        List<ManagerClientDto> clientDtoList = new ArrayList<>();
        clientDtoList.add(getManagerClientDto());
        return new ManagerDto(
                "55555",
                "TestName",
                "Lastname",
                ManagerStatus.ACTIVE,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                clientDtoList
        );
    }

    public static ClientDto getClientDto() {
        return new ClientDto(
                "323e77777-e89b-12d3-a456-426655440000",
                ClientStatus.ACTIVE,
                "308545754255",
                "Aleksey",
                "Lavrov",
                "test@gmail.com",
                "Ensteinstrasse 3",
                "3806668882744",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                getManagerDto()
        );
    }

    public static ManagerClientDto getManagerClientDto() {
        return new ManagerClientDto(
                "323e77777-e89b-12d3-a456-426655440000",
                ClientStatus.ACTIVE,
                "Aleksey",
                "Lavrov"
        );
    }

    public static AccountDto getAccountDto() {
        return new AccountDto(
                "77777777-e89b-12d3-a456-426655440000",
                "Test Name",
                AccountType.CREDIT,
                AccountStatus.ACTIVE,
                new BigDecimal("100000.14"),
                CurrencyType.EUR,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                getClientDto()
        );
    }
}