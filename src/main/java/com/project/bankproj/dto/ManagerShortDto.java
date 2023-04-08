package com.project.bankproj.dto;

import com.project.bankproj.entity.enums.ManagerStatus;
import lombok.Value;

@Value
public class ManagerShortDto {
    String id;
    String firstName;
    String lastName;
    ManagerStatus status;
}