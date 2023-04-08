package com.project.bankproj.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.bankproj.entity.enums.ManagerStatus;
import lombok.Value;

import java.sql.Timestamp;
import java.util.List;

@Value
public class ManagerDto {
    String id;
    String firstName;
    String lastName;
    ManagerStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Timestamp createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Timestamp updatedAt;
    List<ClientShortDto> clients;
}