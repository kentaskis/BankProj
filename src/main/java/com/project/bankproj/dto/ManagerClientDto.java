package com.project.bankproj.dto;

import com.project.bankproj.entity.enums.ClientStatus;
import lombok.Value;

@Value
public class ManagerClientDto {
    String id;
    ClientStatus status;
    String firstName;
    String lastName;
}