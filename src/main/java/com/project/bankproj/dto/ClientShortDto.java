package com.project.bankproj.dto;

import com.project.bankproj.entity.enums.ClientStatus;
import lombok.Value;

@Value
public class ClientShortDto {
    String id;
    ClientStatus status;
    String firstName;
    String lastName;
}