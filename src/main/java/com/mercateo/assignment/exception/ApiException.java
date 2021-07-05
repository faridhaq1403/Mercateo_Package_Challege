package com.mercateo.assignment.exception;

public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 5166405340506429090L;

    public ApiException(String message) {
        super(message);
    }
}