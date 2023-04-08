package com.project.bankproj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.bankproj.dto.CreateManagerDto;
import com.project.bankproj.dto.ManagerDto;
import com.project.bankproj.service.interfaces.ManagerService;
import com.project.bankproj.util.DtoCreator;
import com.project.bankproj.util.JwtCreator;
import com.project.bankproj.validation.JwtFilter;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
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
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private JwtFilter jwtFilter;

    @BeforeEach
    void setup() {
        this.mockMvc =
                MockMvcBuilders.webAppContextSetup(context)
                        .apply(springSecurity())
                        .build();
        // initialisation jsonCreateManagerDto
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void createManager() throws Exception {
        CreateManagerDto createManagerDto = new CreateManagerDto("Aleksey", "Lavrov");
        when(managerService.create(createManagerDto)).thenReturn(DtoCreator.getManagerDto());

        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/manager")
                                .with(jwt().jwt(JwtCreator.getJwt()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        jsonCreateManagerDto.write(createManagerDto).getJson()
                                ))
                .andDo(print())
                .andReturn()
                .getResponse();

        verify(managerService, times(1)).create(createManagerDto);
        assertEquals(response.getStatus(), HttpStatus.CREATED.value());
    }

    @Test
    void getWhichContainsClient() throws Exception {
        final List<ManagerDto> managerDtoList = new ArrayList<>();
        final ManagerDto managerDto = DtoCreator.getManagerDto();
        managerDtoList.add(managerDto);
        when(managerService.getWhichContainsClient()).thenReturn(managerDtoList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/manager/contains-client")
                        .with(jwt().jwt(JwtCreator.getJwt())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(managerDto.getId())))
                .andExpect(jsonPath("$[0].firstName", is(managerDto.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(managerDto.getLastName())))
                .andExpect(jsonPath("$[0].status", is(managerDto.getStatus().toString())))
        ;
    }

    @Test
    void deleteManager() throws Exception {
        int managerId = 1;
        doNothing().when(managerService).delete(managerId);

        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/manager/" + managerId)
                                .with(jwt().jwt(JwtCreator.getJwt())))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        verify(managerService, times(1)).delete(managerId);
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }
}