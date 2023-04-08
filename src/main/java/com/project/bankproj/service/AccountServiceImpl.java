package com.project.bankproj.service;

import com.project.bankproj.dto.AccountDto;
import com.project.bankproj.entity.enums.AccountStatus;
import com.project.bankproj.exeption.AccountNotFoundException;
import com.project.bankproj.exeption.ErrorMessage;
import com.project.bankproj.mapper.AccountMapper;
import com.project.bankproj.repository.AccountRepository;
import com.project.bankproj.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final AccountMapper mapper;

    @Override
    public List<AccountDto> getList() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public List<AccountDto> getListByStatus(String status) {
        return mapper.toDtoList(repository.findAccountByStatus(AccountStatus.valueOf(status.toUpperCase())));
    }

    @Override
    public AccountDto getById(String uuid) {
        return mapper.toDto(
                repository
                        .findById(
                                UUID.fromString(uuid))
                        .orElseThrow(() -> new AccountNotFoundException(ErrorMessage.M_ACCOUNT_NOT_FOUND)));
    }
}