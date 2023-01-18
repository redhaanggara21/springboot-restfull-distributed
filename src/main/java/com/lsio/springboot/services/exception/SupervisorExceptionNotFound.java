package com.lsio.springboot.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SupervisorExceptionNotFound extends RuntimeException {
    private static final long serialVersionUID = -5274604505720526425L;

    public SupervisorExceptionNotFound(String message) {
        super(message);
    }
}
