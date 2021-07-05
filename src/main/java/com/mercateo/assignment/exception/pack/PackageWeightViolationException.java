package com.mercateo.assignment.exception.pack;

public class PackageWeightViolationException extends RuntimeException {

    private static final long serialVersionUID = 5166405340506429090L;

    public PackageWeightViolationException(String message) {
        super(message);
    }
}
