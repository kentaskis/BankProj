package com.project.bankproj.service.interfaces;

import com.project.bankproj.dto.AgreementDto;

import java.util.List;

public interface AgreementService {
    List<AgreementDto> getAgreementsByManagerId(int managerId);
}