package com.project.bankproj.service;

import com.project.bankproj.dto.ClientDto;
import com.project.bankproj.mapper.ClientMapper;
import com.project.bankproj.repository.ClientRepository;
import com.project.bankproj.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDto> getAllWhereTransactionMoreThan(int transactionCount) {
        return clientMapper.toDtoList(
                clientRepository.getAllWhereTransactionCountMoreThen(transactionCount));
    }
}
