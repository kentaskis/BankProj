package com.project.bankproj.mapper;

import com.project.bankproj.dto.AccountDto;
import com.project.bankproj.entity.Account;
import com.project.bankproj.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test class for AccountMapper")
class AccountMapperTest {
    private final AccountMapper accountMapper = new AccountMapperImpl();

    @Test
    @DisplayName("When we have correct entity then return correct AccountDto")
    void toDto() {
        Account account = EntityCreator.getAccount();
        AccountDto accountDto = accountMapper.toDto(account);

        compareEntityWithDto(account, accountDto);
    }

    @Test
    @DisplayName("When we have correct list of Accounts then return correct list of AccountDto")
    void toDtoList() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(EntityCreator.getAccount());
        List<AccountDto> accountDtoList = accountMapper.toDtoList(accountList);

        compareAccountListWithListDto(accountList, accountDtoList);
    }

    private void compareEntityWithDto(Account account, AccountDto dto) {
        assertAll(
                () -> assertEquals(account.getId().toString(), dto.getId()),
                () -> assertEquals(account.getName(), dto.getName()),
                () -> assertEquals(account.getType(), dto.getType()),
                () -> assertEquals(account.getStatus(), dto.getStatus()),
                () -> assertEquals(account.getBalance(), dto.getBalance()),
                () -> assertEquals(account.getCurrency(), dto.getCurrency()),
                () -> assertEquals(account.getCreatedAt(), dto.getCreatedAt()),
                () -> assertEquals(account.getUpdatedAt(), dto.getUpdatedAt())
        );
    }

    private void compareAccountListWithListDto(List<Account> accountList, List<AccountDto> accountDtoList) {
        assertEquals(accountList.size(), accountDtoList.size());
        for (int s = 0; s < accountList.size(); s++) {
            compareEntityWithDto(accountList.get(s), accountDtoList.get(s));
        }
    }
}