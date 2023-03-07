package com.project.bankproj.controller;

import com.project.bankproj.dto.AccountDto;
import com.project.bankproj.service.interfaces.AccountServiceImpl;
import com.project.bankproj.util.DtoCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@DisplayName("Test class for AccountController")
@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
    @Mock
    private AccountServiceImpl accountService;

    @InjectMocks
    private AccountController accountController;

    @Test
    void listAccounts() {
        final List<AccountDto> accountDtoList = new ArrayList<>();
        final AccountDto account = DtoCreator.getAccountDto();
        accountDtoList.add(account);
        when(accountService.getList()).thenReturn(accountDtoList);

        final List<AccountDto> actualList = accountController.listAccounts();

        assertEquals(accountDtoList, actualList);
        verify(accountService).getList();
    }

    @Test
    void get() {
        final AccountDto account = DtoCreator.getAccountDto();
        when(accountService.getById(account.getId())).thenReturn(account);

        final AccountDto actual = accountController.get(account.getId());

        assertNotNull(actual);
        assertEquals(account, actual);
        verify(accountService).getById(account.getId());
    }
}