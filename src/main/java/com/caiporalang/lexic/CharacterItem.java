package com.caiporalang.lexic;

public interface CharacterItem {
    boolean isDecimalPoint(char c);
    boolean isSpace(char c);
    boolean isChar(char c);
    boolean isDigit(char c);
    boolean isOperator(char c);
    boolean isEOF();
    boolean isEOF(char c);
}
