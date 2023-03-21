package com.project.bankproj.mapper;

import com.project.bankproj.dto.AgreementDto;
import com.project.bankproj.entity.Agreement;
import com.project.bankproj.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test class for AgreementMapper")
class AgreementMapperTest {
    private final AgreementMapper agreementMapper = new AgreementMapperImpl();

    @Test
    @DisplayName("When we have correct list of Agreements then return correct list of AgreementDto")
    void toDtoList() {
        List<Agreement> agreementList = new ArrayList<>();
        agreementList.add(EntityCreator.getAgreement());
        List<AgreementDto> agreementDtoList = agreementMapper.toDtoList(agreementList);

        compareAgreementListWithListDto(agreementList, agreementDtoList);
    }

    private void compareEntityWithDto(Agreement agreement, AgreementDto dto) {
        assertAll(
                () -> assertEquals(agreement.getId(), dto.getId()),
                () -> assertEquals(agreement.getStatus(), dto.getStatus()),
                () -> assertEquals(agreement.getInterestRate(), dto.getInterestRate()),
                () -> assertEquals(agreement.getCreatedAt(), dto.getCreatedAt()),
                () -> assertEquals(agreement.getUpdatedAt(), dto.getUpdatedAt())
        );
    }

    private void compareAgreementListWithListDto(List<Agreement> agreementList, List<AgreementDto> agreementDtoList) {
        assertEquals(agreementList.size(), agreementDtoList.size());
        for (int s = 0; s < agreementList.size(); s++) {
            compareEntityWithDto(agreementList.get(s), agreementDtoList.get(s));
        }
    }
}