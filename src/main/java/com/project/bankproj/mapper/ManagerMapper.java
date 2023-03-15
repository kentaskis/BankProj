package com.project.bankproj.mapper;

import com.project.bankproj.dto.CreateManagerDto;
import com.project.bankproj.dto.ManagerClientDto;
import com.project.bankproj.dto.ManagerDto;
import com.project.bankproj.entity.Client;
import com.project.bankproj.entity.Manager;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ClientMapper.class)
public interface ManagerMapper {

    ManagerDto toDto(Manager manager);

    List<ManagerDto> toDtoList(List<Manager> managers);

    Manager toEntity(CreateManagerDto createManagerDto);

    List<ManagerClientDto> toDtoClientList(List<Client> clients);
}