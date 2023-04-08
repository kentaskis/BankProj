package com.project.bankproj.service;

import com.project.bankproj.dto.AgreementDto;
import com.project.bankproj.entity.Agreement;
import com.project.bankproj.mapper.AgreementMapper;
import com.project.bankproj.repository.AgreementRepository;
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

@DisplayName("Test class for AgreementServiceImpl")
@ExtendWith(MockitoExtension.class)
class AgreementServiceImplTest {
    @Mock
    AgreementRepository repository;
    @Mock
    AgreementMapper mapper;
    @InjectMocks
    AgreementServiceImpl agreementService;

    @Test
    void getAgreementsByManagerId() {
        final int MANAGER_ID = 4;
        List<Agreement> agreements = new ArrayList<>();
        agreements.add(EntityCreator.getAgreement());
        List<AgreementDto> agreementDtoList = new ArrayList<>();
        agreementDtoList.add(DtoCreator.getAgreementDto());

        when(repository.findAllAgreementsWhereManagerIdIs(MANAGER_ID)).thenReturn(agreements);
        when(mapper.toDtoList(agreements)).thenReturn(agreementDtoList);

        assertEquals(agreementDtoList, agreementService.getAgreementsByManagerId(MANAGER_ID));
        verify(repository).findAllAgreementsWhereManagerIdIs(MANAGER_ID);
    }
}