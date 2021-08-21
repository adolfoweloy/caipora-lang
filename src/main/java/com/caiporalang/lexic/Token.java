package com.caiporalang.lexic;

public record Token(TokenType tokenType, String value) {

    static Token getEOF() {
        return new Token(TokenType.EOF, "eof");
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenType=" + tokenType +
                ", value='" + value + '\'' +
                '}';
    }
}
