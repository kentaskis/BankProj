package com.project.bankproj.util;

import com.project.bankproj.entity.*;
import com.project.bankproj.entity.enums.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EntityCreator {

    public static Manager getManager() {
        Manager manager = new Manager();
        manager.setId(55555);
        manager.setFirstName("TestName");
        manager.setLastName("Lastname");
        manager.setStatus(ManagerStatus.ACTIVE);

        return manager;
    }

    public static Client getClient() {
        Client client = new Client();
        client.setId(UUID.fromString("323e777-e89b-12d3-a456-66554400"));
        client.setStatus(ClientStatus.ACTIVE);
        client.setEmail("test@gmail.com");
        client.setAddress("Ensteinstrasse 3");
        client.setFirstName("Aleksey");
        client.setLastName("Lavrov");
        client.setPhone("3806668882744");
        client.setTaxCode("308545754255");
        client.setManager(getManager());

        return client;
    }

    public static Account getAccount() {
        Account account = new Account();
        account.setId(UUID.fromString("7777777-e89b-12d3-a456-426654400"));
        account.setType(AccountType.CREDIT);
        account.setBalance(new BigDecimal("100000.14"));
        account.setName("Test Name");
        account.setStatus(AccountStatus.ACTIVE);
        account.setCurrency(CurrencyType.EUR);
        account.setClient(getClient());

        return account;
    }

    public static Agreement getAgreement() {
        return new Agreement(
                333,
                AgreementStatus.ACTIVE,
                new BigDecimal("3.14"),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                getAccount(),
                getProduct()
        );
    }

    public static Product getProduct() {
        List<Agreement> agreementList = new ArrayList<>();
        agreementList.add(new Agreement());
        return new Product(
                3333,
                "Test Name",
                ProductStatus.ACTIVE,
                CurrencyType.EUR,
                new BigDecimal("2.15"),
                1000000,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                getManager(),
                agreementList
        );
    }
}