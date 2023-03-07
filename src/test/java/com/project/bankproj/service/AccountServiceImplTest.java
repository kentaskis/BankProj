package com.project.bankproj.service;

import com.project.bankproj.dto.AccountDto;
import com.project.bankproj.entity.Account;
import com.project.bankproj.mapper.AccountMapper;
import com.project.bankproj.repository.AccountRepository;
import com.project.bankproj.util.DtoCreator;
import com.project.bankproj.util.EntityCreator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Test class for AccountServiceImpl")
@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    AccountRepository repository;
    @Mock
    AccountMapper mapper;
    @InjectMocks
    AccountServiceImpl accountService;

    @AfterEach
    public void clearMocks() {
        clearInvocations(repository, mapper);
    }

    @Test
    void testGetList() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(EntityCreator.getAccount());
        List<AccountDto> accountDtoList = new ArrayList<>();
        accountDtoList.add(DtoCreator.getAccountDto());
        when(repository.findAll()).thenReturn(accounts);
        when(mapper.toDtoList(accounts)).thenReturn(accountDtoList);

        assertEquals(accountDtoList, accountService.getList());
        verify(repository).findAll();
    }

    @Test
    void testGetById() {
        Account account = EntityCreator.getAccount();
        AccountDto accountDto = DtoCreator.getAccountDto();
        when(repository.findById(account.getId())).thenReturn(Optional.of(account));
        when(mapper.toDto(account)).thenReturn(accountDto);

        AccountDto actualAccountDto = accountService.getById(account.getId().toString());

        assertEquals(actualAccountDto, accountDto);
        verify(repository).findById(account.getId());
    }
}