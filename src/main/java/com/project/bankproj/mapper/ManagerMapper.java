package com.project.bankproj.mapper;

import com.project.bankproj.dto.ManagerDto;
import com.project.bankproj.entity.Manager;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ManagerMapper {

    ManagerDto toDto(Manager manager);

    List<ManagerDto> toDtoList(List<Manager> managers);
}