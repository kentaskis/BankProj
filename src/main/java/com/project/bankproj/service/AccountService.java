package com.project.bankproj.service;

import com.project.bankproj.dto.AccountDto;
import com.project.bankproj.dto.CreateAccountDto;
import com.project.bankproj.dto.UpdateAccountDto;
import com.project.bankproj.entity.Account;
import com.project.bankproj.entity.Client;
import com.project.bankproj.exeption.AccountNotFoundException;
import com.project.bankproj.exeption.ClientNotFoundException;
import com.project.bankproj.mapper.AccountMapper;
import com.project.bankproj.mapper.CreateAccountMapper;
import com.project.bankproj.mapper.UUIDMapper;
import com.project.bankproj.repository.AccountRepository;
import com.project.bankproj.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService implements com.project.bankproj.service.interfaces.AccountService {
    private final AccountRepository repository;
    private final ClientRepository clientRepository;
    private final AccountMapper mapper;
    private final CreateAccountMapper accountMapper;
    private final UUIDMapper uuidMapper;

//    @Autowired
//    public AccountService(AccountRepository repository, AccountMapper mapper, UUIDMapper uuidMapper) {
//        this.repository = repository;
//        this.mapper = mapper;
//        this.uuidMapper = uuidMapper;
//    }

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

    @Override
    public void create(CreateAccountDto newAccountDto) {
        Client client = clientRepository
                .findById(uuidMapper.fromString(newAccountDto.getClientId()))
                .orElseThrow(() -> new ClientNotFoundException("Client id is not found"));
        Account account = accountMapper.toDtoModel(newAccountDto);
        account.setClient(client);
        repository.save(account);
    }

    @Override
    public void update(String uuid, UpdateAccountDto accountDto) {

    }

    @Override
    public void delete(String uuid) {

    }
}