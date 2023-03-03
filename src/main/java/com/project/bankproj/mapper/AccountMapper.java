package com.project.bankproj.mapper;

import com.project.bankproj.dto.AccountDto;
import com.project.bankproj.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto toDto(Account account);

    List<AccountDto> toDtoList(List<Account> accounts);
}