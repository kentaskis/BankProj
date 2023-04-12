package com.project.bankproj.exeption;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorCode {
    public static final String ACCOUNT_NOT_FOUND = "account_not_found";
    public static final String VALIDATION_FAILED = "validation_failed";
    public static final String INVALID_PATH_VARIABLE = "invalid_path_variable";
    public static final String JWT_EXPIRED = "token_expired";
    public static final String JWT_NOT_VALID = "token_is_not_valid";
}