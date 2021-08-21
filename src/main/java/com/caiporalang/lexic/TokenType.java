package com.caiporalang.lexic;

public enum TokenType {
    EOF(0),
    IDENTIFIER(1),
    NUMBER(2),
    PUNCTUATION(3),
    OPERATOR(4)
    ;

    private final int id;

    TokenType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TokenType{" +
                "id=" + id + ", " +
                "name=" + name() +
                '}';
    }
}
