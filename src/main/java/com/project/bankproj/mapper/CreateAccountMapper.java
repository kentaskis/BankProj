package com.project.bankproj.mapper;

import com.project.bankproj.dto.CreateAccountDto;
import com.project.bankproj.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateAccountMapper {
    Account toDtoModel(CreateAccountDto createAccountDto);
}