package com.mercateo.assignment.validator;

public enum ValidationRules {

    MAX_PACK_WEIGHT(100, "The maximum weight that a package can hold must be <= 100."),
    MAX_ITEMS_IN_PACK(15, "There may be up to 15 items you can to choose from"),
    MAX_ITEM_WEIGHT(100, "The maximum weight of an item should be <= 100."),
    MAX_ITEM_COST(100, "The maximum cost of an item should be <= €100.");

    private final Integer maxValue;
    private final String errorMessage;

    ValidationRules(Integer value, String message) {
        this.errorMessage = message;
        this.maxValue = value;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Integer getMaxValue() {
        return maxValue;
    }
}
