package com.project.bankproj.util;

import com.project.bankproj.entity.Account;
import com.project.bankproj.entity.Client;
import com.project.bankproj.entity.Manager;
import com.project.bankproj.entity.enums.*;

import java.math.BigDecimal;
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
/*
    public static Agreement getAgreement() {
        Agreement agreement = new Agreement();
        agreement.setId(UUID.fromString("323e4999-0000-1111-a456-426655440000"));
        agreement.setNumber("1472628");
        agreement.setAgreementDate(LocalDate.of(2022, Month.APRIL, 5));
        agreement.setTerminationDate(LocalDate.of(2024, Month.APRIL, 4));
        agreement.setResponsibleSpecialistId("1232asdds55");
        agreement.setActive(true);
        return agreement;
    }

    public static Account getAccount() {
        Account account = new Account();
        account.setId(UUID.fromString("00009999-2222-1111-a456-426655440000"));
        account.setAccountNumber("459200");
        account.setPrincipalDebt(new BigDecimal("100550.55"));
        account.setInterestDebt(new BigDecimal("14000"));
        account.setActive(true);
        account.setOpeningDate(LocalDate.of(2022, Month.APRIL, 10));
        account.setOutstandingPrincipal(new BigDecimal("20000"));
        account.setOutstandingInterestDebt(new BigDecimal("200"));
        account.setCurrencyCode("USD");
        return account;
    }

    public static PaymentSchedule getPaymentSchedule(LocalDate date) {
        PaymentSchedule schedule = new PaymentSchedule();
        schedule.setId(UUID.randomUUID());
        schedule.setPaymentDate(date);
        schedule.setPrincipal(new BigDecimal("1000"));
        schedule.setInterest(new BigDecimal("100"));
        return schedule;
    }

    public static Credit prepareCreditMappingPaymentScheduleDto() {
        Credit credit = EntityCreator.getCredit();
        credit.setAgreement(EntityCreator.getAgreement());
        credit.setAccount(EntityCreator.getAccount());
        List<PaymentSchedule> schedule =
                List.of(EntityCreator.getPaymentSchedule(LocalDate.of(2022, Month.APRIL, 20)),
                        EntityCreator.getPaymentSchedule(LocalDate.of(2022, Month.MAY, 20)),
                        EntityCreator.getPaymentSchedule(LocalDate.of(2022, Month.JUNE, 20)));
        credit.getAccount().setPaymentSchedule(schedule);
        return credit;
    }

    public static Product getProduct() {
        Product product = new Product();
        product.setId(new BigInteger("1"));
        product.setName("Credit for wedding");
        product.setMinSum(new BigDecimal("5.3"));
        product.setMaxSum(new BigDecimal("10.1"));
        product.setCurrencyCode("RUB");
        product.setMinInterestRate(new BigDecimal("2.3"));
        product.setMaxInterestRate(new BigDecimal("5.6"));
        product.setNeedGuarantees(true);
        product.setDeliveryInCash(true);
        product.setEarlyRepayment(true);
        product.setNeedIncomeDetails(true);
        product.setNeedGuarantees(true);
        product.setMinPeriodMonths(12);
        product.setMaxPeriodMonths(360);
        product.setActive(true);
        product.setDetails("Info for this credit");
        product.setCalculationMode(CalculationMode.ANNUITY);
        product.setGracePeriodMonths(2);
        product.setRateIsAdjustable(true);
        product.setRateBase("CB RF");
        product.setRateFixPart(new BigDecimal("1.1"));
        product.setIncreasedRate(new BigDecimal("2.0"));
        return product;
    }

    public static Card getCard() {
        Card card = new Card();
        card.setId(UUID.fromString("923e4567-e89b-12d3-a456-426655440000"));
        card.setCardNumber("9483748374149384");
        card.setHolderName("Mikhail");
        card.setExpirationDate(LocalDate.of(2024, 3, 8));
        card.setBalance(new BigDecimal("3500000"));
        card.setTransactionLimit(new BigDecimal("100000"));
        card.setDeliveryPoint("Delivery point");
        card.setDigitalValet(true);
        card.setVirtual(true);
        card.setCoBrand("brand");
        card.setPaymentSystem(PaymentSystem.VISA);
        card.setStatus(CardStatus.ACTIVE);
        card.setAccount(getAccount());
        return card;
    }

 */
}