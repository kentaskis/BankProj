package com.project.bankproj.controller;

import com.project.bankproj.dto.AgreementDto;
import com.project.bankproj.service.interfaces.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agreement")
@RequiredArgsConstructor
public class AgreementController {

    private final AgreementService agreementService;

    @GetMapping("by-manager-id/{managerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<AgreementDto> getAgreementsWhereManagerIdIs(@PathVariable int managerId) {
        return agreementService.getAgreementsByManagerId(managerId);
    }
}