package com.caiporalang.lexic.exception;

public class InvalidIdentifier extends RuntimeException {
    private final String identifier;

    public InvalidIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getMessage() {
        return "Invalid identifier {" + identifier + "}";
    }
}
