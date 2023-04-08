package com.project.bankproj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.bankproj.dto.CreateManagerDto;
import com.project.bankproj.exeption.AccountNotFoundException;
import com.project.bankproj.exeption.ErrorCode;
import com.project.bankproj.service.interfaces.AccountService;
import com.project.bankproj.service.interfaces.ManagerService;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest({AccountController.class, ManagerController.class, ResponseExceptionHandler.class})
@DisplayName("Test ResponseExceptionHandler")
class ResponseExceptionHandlerTest {
    @MockBean
    AccountService accountService;
    @MockBean
    ManagerService managerService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ResponseExceptionHandler responseExceptionHandler;
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
    void handleAccountNotFoundException() throws Exception {
        String validUUID = "72c98cd7-b1d5-11ed-8545-08979887bb12";

        when(accountService.getById(validUUID)).thenThrow(AccountNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/account/" + validUUID)
                        .with(jwt().jwt(JwtCreator.getJwt())))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.ACCOUNT_NOT_FOUND))
        ;
    }

    @Test
    void testHandleMethodArgumentNotValid() throws Exception {
        CreateManagerDto createManagerDto = new CreateManagerDto("A", "L");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/manager")
                                .with(jwt().jwt(JwtCreator.getJwt()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        jsonCreateManagerDto.write(createManagerDto).getJson()
                                ))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.VALIDATION_FAILED));
    }

    @Test
    void handleConstraintViolationException() throws Exception {
        String wrongUUID = "fjhfljhflasjhdfasdf";

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/account/" + wrongUUID)
                        .with(jwt().jwt(JwtCreator.getJwt())))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.INVALID_PATH_VARIABLE))
        ;
    }
}