package com.project.bankproj.service.interfaces;

import com.project.bankproj.dto.AccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getList();
    List<AccountDto> getListByStatus(String status);
    AccountDto getById(String uuid);
}