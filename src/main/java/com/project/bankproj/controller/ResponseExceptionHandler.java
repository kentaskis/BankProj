package com.project.bankproj.controller;

import com.project.bankproj.dto.ErrorExtension;
import com.project.bankproj.dto.ErrorResponse;
import com.project.bankproj.exeption.AccountNotFoundException;
import com.project.bankproj.exeption.ClientNotFoundException;
import com.project.bankproj.exeption.ErrorCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleAccountNotFoundException(Exception e) {

        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(),
                ErrorCode.ACCOUNT_NOT_FOUND
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleClientNotFoundException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(),
                ErrorCode.CLIENT_NOT_FOUND
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorExtension> handleIllegalArgumentException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(),
                ErrorCode.VALIDATION_FAILED
        ), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        List<ErrorExtension> errorExtensions = ex.getFieldErrors()
                .stream()
                .map(filedError -> new ErrorExtension(filedError.getDefaultMessage(),
                        String.format("invalid_%s", filedError.getField())))
                .collect(Collectors.toList());
        return new ResponseEntity<>(
                new ErrorResponse(ErrorCode.VALIDATION_FAILED, errorExtensions), HttpStatus.BAD_REQUEST);
    }
}