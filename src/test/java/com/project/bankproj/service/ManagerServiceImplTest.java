package com.project.bankproj.service;

import com.project.bankproj.dto.CreateManagerDto;
import com.project.bankproj.dto.ManagerDto;
import com.project.bankproj.entity.Manager;
import com.project.bankproj.mapper.ManagerMapper;
import com.project.bankproj.repository.ManagerRepository;
import com.project.bankproj.util.DtoCreator;
import com.project.bankproj.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Test class for ManagerServiceImpl")
@ExtendWith(MockitoExtension.class)
class ManagerServiceImplTest {
    @Mock
    ManagerRepository repository;
    @Mock
    ManagerMapper mapper;
    @InjectMocks
    ManagerServiceImpl managerService;

    @Test
    void getWhichContainsClient() {
        List<Manager> managers = new ArrayList<>();
        managers.add(EntityCreator.getManager());
        List<ManagerDto> managerDtoList = new ArrayList<>();
        managerDtoList.add(DtoCreator.getManagerDto());
        when(repository.findAllWhichContainsClient()).thenReturn(managers);
        when(mapper.toDtoList(managers)).thenReturn(managerDtoList);

        assertEquals(managerDtoList, managerService.getWhichContainsClient());
        verify(repository).findAllWhichContainsClient();
    }

    @Test
    void create() {
        CreateManagerDto dto = DtoCreator.getCreateManagerDto();
        Manager manager = EntityCreator.getManager();

        when(repository.save(manager)).thenReturn(manager);
        when(mapper.toEntity(dto)).thenReturn(manager);

        managerService.create(dto);
        verify(repository).save(manager);
        verify(mapper).toEntity(dto);
    }
}