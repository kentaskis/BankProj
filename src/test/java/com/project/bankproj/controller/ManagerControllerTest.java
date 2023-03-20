package com.project.bankproj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.bankproj.dto.CreateManagerDto;
import com.project.bankproj.dto.ManagerDto;
import com.project.bankproj.service.interfaces.ManagerService;
import com.project.bankproj.util.DtoCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(ManagerController.class)
@DisplayName("Test class for ManagerController")
class ManagerControllerTest {
    @MockBean
    private ManagerService managerService;
    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<CreateManagerDto> jsonCreateManagerDto;

    @BeforeEach
    public void setup() {
        // initialisation jsonCreateManagerDto
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void createManager() throws Exception {
        CreateManagerDto createManagerDto = new CreateManagerDto("Aleksey", "Lavrov");
        doNothing().when(managerService).create(createManagerDto);
        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/manager")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        jsonCreateManagerDto.write(createManagerDto).getJson()
                                ))
                .andReturn()
                .getResponse();

        verify(managerService, times(1)).create(createManagerDto);
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @Test
    void getWhichContainsClient() throws Exception {
        final List<ManagerDto> managerDtoList = new ArrayList<>();
        final ManagerDto managerDto = DtoCreator.getManagerDto();
        managerDtoList.add(managerDto);
        when(managerService.getWhichContainsClient()).thenReturn(managerDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/manager/contains-client"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(managerDto.getId())))
                .andExpect(jsonPath("$[0].firstName", is(managerDto.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(managerDto.getLastName())))
                .andExpect(jsonPath("$[0].status", is(managerDto.getStatus().toString())))
        ;
    }
}