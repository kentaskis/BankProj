package com.project.bankproj.controller;

import com.project.bankproj.dto.ClientDto;
import com.project.bankproj.service.interfaces.ClientService;
import com.project.bankproj.util.DtoCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(ClientController.class)
@DisplayName("Test class for ClientController")
class ClientControllerTest {
    @MockBean
    private ClientService clientService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllClientsWhereTransactionMoreThan() throws Exception {
        final int TRANSACTION_COUNT = 4;
        final List<ClientDto> clientDtoList = new ArrayList<>();
        final ClientDto clientDto = DtoCreator.getClientDto();
        clientDtoList.add(clientDto);
        when(clientService.getAllWhereTransactionMoreThan(TRANSACTION_COUNT)).thenReturn(clientDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/client/where-transaction-more-than/" + TRANSACTION_COUNT))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(clientDto.getId())))
                .andExpect(jsonPath("$[0].firstName", is(clientDto.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(clientDto.getLastName())))
                .andExpect(jsonPath("$[0].status", is(clientDto.getStatus().toString())))
                .andExpect(jsonPath("$[0].taxCode", is(clientDto.getTaxCode())))
                .andExpect(jsonPath("$[0].email", is(clientDto.getEmail())))
                .andExpect(jsonPath("$[0].address", is(clientDto.getAddress())))
                .andExpect(jsonPath("$[0].phone", is(clientDto.getPhone())))
        ;
    }
}