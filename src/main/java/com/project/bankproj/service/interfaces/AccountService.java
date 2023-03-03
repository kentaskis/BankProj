package com.project.bankproj.service.interfaces;

import com.project.bankproj.dto.AccountDto;
import com.project.bankproj.dto.CreateAccountDto;
import com.project.bankproj.dto.UpdateAccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getList();

    AccountDto getById(String uuid);

    void create(CreateAccountDto newAccountDto);

    void update(String uuid, UpdateAccountDto accountDto);

    void delete(String uuid);
}
