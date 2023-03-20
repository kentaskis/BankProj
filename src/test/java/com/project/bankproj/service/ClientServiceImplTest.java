package com.project.bankproj.service;

import com.project.bankproj.dto.ClientDto;
import com.project.bankproj.entity.Client;
import com.project.bankproj.mapper.ClientMapper;
import com.project.bankproj.repository.ClientRepository;
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

@DisplayName("Test class for ClientServiceImpl")
@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {
    @Mock
    ClientRepository repository;
    @Mock
    ClientMapper mapper;
    @InjectMocks
    ClientServiceImpl clientService;

    @Test
    void getAllWhereTransactionMoreThan() {
        final int TRANSACTION_COUNT = 4;
        List<Client> clientList = new ArrayList<>();
        clientList.add(EntityCreator.getClient());
        List<ClientDto> clientDtoList = new ArrayList<>();
        clientDtoList.add(DtoCreator.getClientDto());
        when(repository.getAllWhereTransactionCountMoreThen(TRANSACTION_COUNT)).thenReturn(clientList);
        when(mapper.toDtoList(clientList)).thenReturn(clientDtoList);

        assertEquals(clientDtoList, clientService.getAllWhereTransactionMoreThan(TRANSACTION_COUNT));
        verify(repository).getAllWhereTransactionCountMoreThen(TRANSACTION_COUNT);
    }
}