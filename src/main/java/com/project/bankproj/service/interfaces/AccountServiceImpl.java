package com.project.bankproj.service.interfaces;

import com.project.bankproj.dto.AccountDto;

import java.util.List;

public interface AccountServiceImpl {
    List<AccountDto> getList();

    AccountDto getById(String uuid);
}