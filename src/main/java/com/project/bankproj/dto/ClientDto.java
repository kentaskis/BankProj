package com.project.bankproj.dto;

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
    Timestamp createdAt;
    Timestamp updatedAt;
}