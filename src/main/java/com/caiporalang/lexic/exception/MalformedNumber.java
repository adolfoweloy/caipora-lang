package com.caiporalang.lexic.exception;

public class MalformedNumber extends RuntimeException {
    private final String number;

    public MalformedNumber(String number) {
        this.number = number;
    }

    @Override
    public String getMessage() {
        return "Malformed number {" + number + "}";
    }
}
