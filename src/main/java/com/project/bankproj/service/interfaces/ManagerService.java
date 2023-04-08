package com.project.bankproj.service.interfaces;

import com.project.bankproj.dto.CreateManagerDto;
import com.project.bankproj.dto.ManagerDto;

import java.util.List;

public interface ManagerService {
    List<ManagerDto> getWhichContainsClient();

    ManagerDto create(CreateManagerDto createManagerDto);

    void delete(int managerId);
}