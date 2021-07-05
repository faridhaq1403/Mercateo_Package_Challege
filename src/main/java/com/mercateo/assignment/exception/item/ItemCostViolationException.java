package com.mercateo.assignment.exception.item;

public class ItemCostViolationException extends RuntimeException {

    private static final long serialVersionUID = 5166405340506429090L;

    public ItemCostViolationException(String message) {
        super(message);
    }
}

