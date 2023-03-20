package com.project.bankproj.controller;

import com.project.bankproj.dto.ClientDto;
import com.project.bankproj.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/where-transaction-more-than/{transactionCount}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getAllClientsWhereTransactionMoreThan(@PathVariable Integer transactionCount) {
        return clientService.getAllWhereTransactionMoreThan(transactionCount);
    }
}
