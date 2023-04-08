package com.project.bankproj.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.bankproj.entity.enums.ClientStatus;
import lombok.Value;

import java.sql.Timestamp;

@Value
public class ClientDto {
    String id;
    ClientStatus status;
    String taxCode;
    String firstName;
    String lastName;
    String email;
    String address;
    String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Timestamp createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Timestamp updatedAt;
}