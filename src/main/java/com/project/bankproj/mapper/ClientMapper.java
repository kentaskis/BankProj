package com.project.bankproj.mapper;

import com.project.bankproj.dto.ClientDto;
import com.project.bankproj.entity.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto toDto(Client client);

    List<ClientDto> toDtoList(List<Client> clients);
}