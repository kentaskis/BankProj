package com.project.bankproj.mapper;

import com.project.bankproj.dto.CreateManagerDto;
import com.project.bankproj.dto.ManagerClientDto;
import com.project.bankproj.dto.ManagerDto;
import com.project.bankproj.entity.Client;
import com.project.bankproj.entity.Manager;
import com.project.bankproj.util.DtoCreator;
import com.project.bankproj.util.EntityCreator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ManagerMapperTest {
    private final ManagerMapper managerMapper = new ManagerMapperImpl();

    @Test
    void toDto() {
        Manager manager = EntityCreator.getManager();
        ManagerDto managerDto = managerMapper.toDto(manager);

        compareEntityWithDto(manager, managerDto);
    }

    @Test
    void toDtoList() {
        List<Manager> managerList = new ArrayList<>();
        managerList.add(EntityCreator.getManager());
        List<ManagerDto> managerDtoList = managerMapper.toDtoList(managerList);

        compareManagerListWithListDto(managerList, managerDtoList);
    }

    @Test
    void toEntity() {
        CreateManagerDto createManagerDto = DtoCreator.getCreateManagerDto();
        Manager manager = managerMapper.toEntity(createManagerDto);

        compareCreateEntityWithDto(manager, createManagerDto);
    }

    @Test
    void toDtoClientList() {
        List<Client> clientList = new ArrayList<>();
        clientList.add(EntityCreator.getClient());
        List<ManagerClientDto> managerClientDtos = managerMapper.toDtoClientList(clientList);

        compareClientListWithListDto(clientList, managerClientDtos);
    }

    private void compareClientEntityWithDto(Client client, ManagerClientDto dto) {
        assertAll(
                () -> assertEquals(client.getId().toString(), dto.getId()),
                () -> assertEquals(client.getFirstName(), dto.getFirstName()),
                () -> assertEquals(client.getLastName(), dto.getLastName()),
                () -> assertEquals(client.getStatus(), dto.getStatus())
        );
    }

    private void compareClientListWithListDto(List<Client> clientList, List<ManagerClientDto> clientDtoList) {
        assertEquals(clientList.size(), clientDtoList.size());
        for (int s = 0; s < clientList.size(); s++) {
            compareClientEntityWithDto(clientList.get(s), clientDtoList.get(s));
        }
    }

    private void compareCreateEntityWithDto(Manager manager, CreateManagerDto dto) {
        assertAll(
                () -> assertEquals(manager.getFirstName(), dto.getFirstName()),
                () -> assertEquals(manager.getLastName(), dto.getLastName())
        );
    }

    private void compareEntityWithDto(Manager manager, ManagerDto dto) {
        assertAll(
                () -> assertEquals(Integer.toString(manager.getId()), dto.getId()),
                () -> assertEquals(manager.getFirstName(), dto.getFirstName()),
                () -> assertEquals(manager.getLastName(), dto.getLastName()),
                () -> assertEquals(manager.getStatus(), dto.getStatus()),
                () -> assertEquals(manager.getCreatedAt(), dto.getCreatedAt()),
                () -> assertEquals(manager.getUpdatedAt(), dto.getUpdatedAt())
        );
    }

    private void compareManagerListWithListDto(List<Manager> managerList, List<ManagerDto> managerDtoList) {
        assertEquals(managerList.size(), managerDtoList.size());
        for (int s = 0; s < managerList.size(); s++) {
            compareEntityWithDto(managerList.get(s), managerDtoList.get(s));
        }
    }
}