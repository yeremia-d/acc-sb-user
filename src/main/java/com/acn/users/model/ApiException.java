package com.acn.users.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiException {
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
}
