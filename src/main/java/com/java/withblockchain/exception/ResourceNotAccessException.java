package com.java.withblockchain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceNotAccessException extends RuntimeException {
    public ResourceNotAccessException() {
        super(String.format("Resource not access!"));
    }
}