package com.project.bankproj.service;

import com.project.bankproj.dto.AgreementDto;
import com.project.bankproj.mapper.AgreementMapper;
import com.project.bankproj.repository.AgreementRepository;
import com.project.bankproj.service.interfaces.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    private final AgreementMapper agreementMapper;
    private final AgreementRepository agreementRepository;

    @Override
    public List<AgreementDto> getAgreementsByManagerId(int managerId) {
        return agreementMapper.toDto(
                agreementRepository.findAllAgreementsWhereManagerIdIs(managerId));
    }
}