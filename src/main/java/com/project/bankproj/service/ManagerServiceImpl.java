package com.project.bankproj.service;

import com.project.bankproj.dto.CreateManagerDto;
import com.project.bankproj.dto.ManagerDto;
import com.project.bankproj.entity.Manager;
import com.project.bankproj.entity.enums.ManagerStatus;
import com.project.bankproj.mapper.ManagerMapper;
import com.project.bankproj.repository.ManagerRepository;
import com.project.bankproj.service.interfaces.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository repository;
    private final ManagerMapper mapper;

    @Override
    public List<ManagerDto> getWhichContainsClient() {
        return mapper.toDtoList(repository.findAllWhichContainsClient());
    }

    @Override
    public ManagerDto create(CreateManagerDto createManagerDto) {
        Manager manager = mapper.toEntity(createManagerDto);
        manager.setStatus(ManagerStatus.PENDING);
        return mapper.toDto(repository.save(manager));
    }

    @Override
    public void delete(int managerId) {
        repository.deleteById(managerId);
    }
}