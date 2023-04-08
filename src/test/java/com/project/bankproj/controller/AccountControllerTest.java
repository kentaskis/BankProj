package com.project.bankproj.controller;

import com.project.bankproj.dto.AccountDto;
import com.project.bankproj.entity.enums.AccountStatus;
import com.project.bankproj.service.interfaces.AccountService;
import com.project.bankproj.util.DtoCreator;
import com.project.bankproj.util.JwtCreator;
import com.project.bankproj.validation.JwtFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = AccountController.class,
        excludeAutoConfiguration = {
                UserDetailsServiceAutoConfiguration.class, SecurityAutoConfiguration.class
        })
@DisplayName("Test class for AccountController")
class AccountControllerTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private JwtFilter jwtFilter;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        this.mockMvc =
                MockMvcBuilders.webAppContextSetup(context)
                        .apply(springSecurity())
                        .build();
    }

    @Test
    void listAccounts() throws Exception {
        final List<AccountDto> accountDtoList = new ArrayList<>();
        final AccountDto account = DtoCreator.getAccountDto();
        accountDtoList.add(account);
        when(accountService.getList()).thenReturn(accountDtoList);
        mockMvc
                .perform(MockMvcRequestBuilders.get("/account").with(jwt().jwt(JwtCreator.getJwt())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(account.getId())))
                .andExpect(jsonPath("$[0].name", is(account.getName())))
                .andExpect(jsonPath("$[0].status", is(account.getStatus().toString())))
                .andExpect(jsonPath("$[0].currency", is(account.getCurrency().toString())))
                .andExpect(jsonPath("$[0].balance").value(account.getBalance().toString()));
    }

    @Test
    void get() throws Exception {
        final AccountDto account = DtoCreator.getAccountDto();
        when(accountService.getById(account.getId())).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.get("/account/" + account.getId()).with(jwt().jwt(JwtCreator.getJwt())))
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

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/account/status/" + AccountStatus.ACTIVE)
                                .with(jwt().jwt(JwtCreator.getJwt())))
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