package com.biblioteca.auth.infra.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException{

    public ValidationException(String message) {
        super(message);
    }

}
