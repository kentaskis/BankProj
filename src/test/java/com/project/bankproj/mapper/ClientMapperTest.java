package com.project.bankproj.mapper;

import com.project.bankproj.dto.ClientDto;
import com.project.bankproj.entity.Client;
import com.project.bankproj.util.EntityCreator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientMapperTest {
    private final ClientMapper clientMapper = new ClientMapperImpl();

    @Test
    void toDto() {
        Client client = EntityCreator.getClient();
        ClientDto clientDtos = clientMapper.toDto(client);

        compareClientEntityWithDto(client, clientDtos);
    }

    @Test
    void toDtoList() {
        List<Client> clientList = new ArrayList<>();
        clientList.add(EntityCreator.getClient());
        List<ClientDto> clientDtos = clientMapper.toDtoList(clientList);

        compareClientListWithListDto(clientList, clientDtos);
    }

    private void compareClientEntityWithDto(Client client, ClientDto dto) {
        assertAll(
                () -> assertEquals(client.getId().toString(), dto.getId()),
                () -> assertEquals(client.getTaxCode(), dto.getTaxCode()),
                () -> assertEquals(client.getFirstName(), dto.getFirstName()),
                () -> assertEquals(client.getLastName(), dto.getLastName()),
                () -> assertEquals(client.getEmail(), dto.getEmail()),
                () -> assertEquals(client.getAddress(), dto.getAddress()),
                () -> assertEquals(client.getPhone(), dto.getPhone()),
                () -> assertEquals(client.getStatus(), dto.getStatus()),
                () -> assertEquals(client.getCreatedAt(), dto.getCreatedAt()),
                () -> assertEquals(client.getUpdatedAt(), dto.getUpdatedAt())
        );
    }

    private void compareClientListWithListDto(List<Client> clientList, List<ClientDto> clientDtoList) {
        assertEquals(clientList.size(), clientDtoList.size());
        for (int s = 0; s < clientList.size(); s++) {
            compareClientEntityWithDto(clientList.get(s), clientDtoList.get(s));
        }
    }
}