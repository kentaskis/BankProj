package com.project.bankproj.controller;

import com.project.bankproj.dto.AccountDto;
import com.project.bankproj.service.interfaces.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceImpl service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> listAccounts() {
        return service.getList();
    }

    @GetMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto get(@PathVariable String uuid) {
        return service.getById(uuid);
    }
}