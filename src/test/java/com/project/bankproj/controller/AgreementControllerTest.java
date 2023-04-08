package com.project.bankproj.controller;

import com.project.bankproj.dto.AgreementDto;
import com.project.bankproj.service.interfaces.AgreementService;
import com.project.bankproj.util.DtoCreator;
import com.project.bankproj.util.JwtCreator;
import com.project.bankproj.validation.JwtFilter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@WebMvcTest(AgreementController.class)
@DisplayName("Test class for AgreementController")
class AgreementControllerTest {
    @MockBean
    AgreementService agreementService;
    @Autowired
    MockMvc mockMvc;
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
    }

    @Test
    void getAgreementsWhereManagerIdIs() throws Exception {
        final int MANAGER_ID = 4;
        final AgreementDto agreementDto = DtoCreator.getAgreementDto();
        final List<AgreementDto> agreementDtoList = new ArrayList<>();
        agreementDtoList.add(agreementDto);

        Mockito.when(agreementService.getAgreementsByManagerId(MANAGER_ID)).thenReturn(agreementDtoList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/agreement/by-manager-id/" + MANAGER_ID)
                        .with(jwt().jwt(JwtCreator.getJwt())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(agreementDto.getId())))
                .andExpect(jsonPath("$[0].interestRate").value(agreementDto.getInterestRate().toString()))
                .andExpect(jsonPath("$[0].status", is(agreementDto.getStatus().toString())))
        ;
    }
}