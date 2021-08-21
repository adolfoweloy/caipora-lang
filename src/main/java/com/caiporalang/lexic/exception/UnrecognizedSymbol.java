package com.caiporalang.lexic.exception;

public class UnrecognizedSymbol extends RuntimeException {
    private final char symbol;

    public UnrecognizedSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getMessage() {
        return "Unrecognized symbol {" + symbol + "}";
    }
}
