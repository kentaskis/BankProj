package com.project.bankproj.controller;

import com.project.bankproj.dto.AccountDto;
import com.project.bankproj.dto.CreateAccountDto;
import com.project.bankproj.dto.UpdateAccountDto;
import com.project.bankproj.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

//    @Autowired
//    public AccountController(AccountServiceIf service) {
//        this.service = service;
//    }

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

    @PostMapping
    public void create(@RequestBody CreateAccountDto accountData) {
        service.create(accountData);
    }

    @PutMapping("{uuid}")
    public void update(@PathVariable String uuid, @RequestBody UpdateAccountDto accountData) {
        service.update(uuid, accountData);
    }

    @DeleteMapping("{uuid}")
    public void delete(@PathVariable String uuid) {
        service.delete(uuid);
    }
}