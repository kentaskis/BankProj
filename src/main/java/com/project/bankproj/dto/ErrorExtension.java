package com.project.bankproj.dto;

import lombok.Value;

@Value
public class ErrorExtension {
    String message;
    String errorCode;
}