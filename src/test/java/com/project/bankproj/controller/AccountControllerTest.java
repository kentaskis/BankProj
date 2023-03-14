package com.project.bankproj.controller;

import com.project.bankproj.dto.AccountDto;
import com.project.bankproj.entity.enums.AccountStatus;
import com.project.bankproj.service.interfaces.AccountService;
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
@WebMvcTest(AccountController.class)
@DisplayName("Test class for AccountController")
class AccountControllerTest {
    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listAccounts() throws Exception {
        final List<AccountDto> accountDtoList = new ArrayList<>();
        final AccountDto account = DtoCreator.getAccountDto();
        accountDtoList.add(account);
        when(accountService.getList()).thenReturn(accountDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/account"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(account.getId())))
                .andExpect(jsonPath("$[0].name", is(account.getName())))
                .andExpect(jsonPath("$[0].status", is(account.getStatus().toString())))
                .andExpect(jsonPath("$[0].currency", is(account.getCurrency().toString())))
                .andExpect(jsonPath("$[0].balance").value(account.getBalance().toString()))
        ;

    }

    @Test
    void get() throws Exception {
        final AccountDto account = DtoCreator.getAccountDto();
        when(accountService.getById(account.getId())).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.get("/account/" + account.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(account.getId()))
                .andExpect(jsonPath("$.name").value(account.getName()))
                .andExpect(jsonPath("$.status", is(account.getStatus().toString())))
                .andExpect(jsonPath("$.currency", is(account.getCurrency().toString())))
                .andExpect(jsonPath("$.balance").value(account.getBalance()));
    }

    @Test
    void listAccountsByStatus() throws Exception {
        final List<AccountDto> accountDtoList = new ArrayList<>();
        final AccountDto account = DtoCreator.getAccountDto();
        accountDtoList.add(account);
        when(accountService.getListByStatus(AccountStatus.ACTIVE.toString())).thenReturn(accountDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/account/status/"+ AccountStatus.ACTIVE))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(account.getId())))
                .andExpect(jsonPath("$[0].name", is(account.getName())))
                .andExpect(jsonPath("$[0].status", is(account.getStatus().toString())))
                .andExpect(jsonPath("$[0].currency", is(account.getCurrency().toString())))
                .andExpect(jsonPath("$[0].balance").value(account.getBalance().toString()))
        ;
    }
}