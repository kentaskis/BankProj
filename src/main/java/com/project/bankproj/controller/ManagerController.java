package com.project.bankproj.controller;

import com.project.bankproj.dto.CreateManagerDto;
import com.project.bankproj.dto.ManagerDto;
import com.project.bankproj.service.interfaces.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("manager")
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createManager(@Valid @RequestBody CreateManagerDto createManagerDto) {
        managerService.create(createManagerDto);
    }

    @GetMapping("/contains-client")
    @ResponseStatus(HttpStatus.OK)
    public List<ManagerDto> getWhichContainsClient() {
        return managerService.getWhichContainsClient();
    }
}
