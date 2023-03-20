package com.project.bankproj.service.interfaces;

import com.project.bankproj.dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAllWhereTransactionMoreThan(int transactionCount);
}
