package com.caiporalang.lexic.exception;

public class InvalidOperator extends RuntimeException {
    private final String operator;

    public InvalidOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String getMessage() {
        return "Invalid operator {" + operator + "}";
    }
}
