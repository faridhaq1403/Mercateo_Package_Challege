package com.mercateo.assignment.exception.item;

public class ItemWeightViolationException extends RuntimeException {

    private static final long serialVersionUID = 5166405340506429090L;

    public ItemWeightViolationException(String message) {
        super(message);
    }
}

