package com.project.bankproj.service;

import com.project.bankproj.dto.AccountDto;
import com.project.bankproj.exeption.AccountNotFoundException;
import com.project.bankproj.mapper.AccountMapper;
import com.project.bankproj.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements com.project.bankproj.service.interfaces.AccountServiceImpl {
    private final AccountRepository repository;
    private final AccountMapper mapper;

    @Override
    public List<AccountDto> getList() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public AccountDto getById(String uuid) {
        return mapper.toDto(
                repository
                        .findById(
                                UUID.fromString(uuid))
                        .orElseThrow(() -> new AccountNotFoundException("Account id is not found")));
    }
}