package com.project.bankproj.util;


import com.project.bankproj.dto.*;
import com.project.bankproj.entity.enums.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DtoCreator {
    public static ManagerDto getManagerDto() {
        List<ClientShortDto> clientDtoList = new ArrayList<>();
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

    public static ManagerShortDto getManagerShortDto() {
        return new ManagerShortDto(
                "55555",
                "TestName",
                "Lastname",
                ManagerStatus.ACTIVE
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
                new Timestamp(System.currentTimeMillis())
        );
    }

    public static ClientShortDto getManagerClientDto() {
        return new ClientShortDto(
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

    public static CreateManagerDto getCreateManagerDto() {
        return new CreateManagerDto("Alexey", "Lavrov");
    }

    public static AgreementDto getAgreementDto() {
        return new AgreementDto(
                333,
                AgreementStatus.ACTIVE,
                new BigDecimal("3.14"),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                getAccountShortDto(),
                getProductDto()
        );
    }

    public static AccountShortDto getAccountShortDto() {
        return new AccountShortDto(
                "77777777-e89b-12d3-a456-426655440000",
                "Test Name",
                AccountType.CREDIT,
                AccountStatus.ACTIVE,
                new BigDecimal("100000.14"),
                CurrencyType.EUR
        );
    }

    public static ProductDto getProductDto() {
        return new ProductDto(
                3333,
                "Test Name",
                ProductStatus.ACTIVE,
                CurrencyType.EUR,
                new BigDecimal("2.15"),
                1000000,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                getManagerShortDto()
        );
    }

    public static JwtRequest getJwtRequest() {
        return new JwtRequest(
                "testLogin",
                "testPassword"
        );
    }

    public static JwtResponse getJwtResponse() {
        return new JwtResponse(
                "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4MDYzMTA1Miwicm9sZXMiOlsiQURNSU4iXSwiZmlyc3ROYW1lIjoiYWRtaW4ifQ.5ZmZlVXaefELt4wa-1oOgguBJ33S2MVHOHruDPs5QEkrVbnrmn99F_v01jY7fzkekH-FG-O6vEcokCGRB-MBzA",
                "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4MzIyMjQ1MX0.WPGYpABeheuSTqZ4AlCW_-Fj3jPClDqWn6jtJbYEWydaHof8Fcl1HZCAob60s1Pq8LPXHQFPwNRFBD56eaCcJA"
        );
    }

    public static RefreshJwtRequest geRefreshJwtRequest() {
        return new RefreshJwtRequest(
                "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4MzIyMjQ1MX0.WPGYpABeheuSTqZ4AlCW_-Fj3jPClDqWn6jtJbYEWydaHof8Fcl1HZCAob60s1Pq8LPXHQFPwNRFBD56eaCcJA"
        );
    }
}