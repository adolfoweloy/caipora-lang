package com.caiporalang.lexic;

import com.caiporalang.lexic.state.States;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScannerContext implements CharacterItem {
    private final char[] content;
    private int position;

    ScannerContext(String filePath) throws IOException {
        Path inputPath = FileSystems.getDefault().getPath(filePath);
        this.position = 0;
        this.content = new String(Files.readAllBytes(inputPath)).toCharArray();
    }

    public Token nextToken() {
        if (isEOF()) return Token.getEOF();
        return States.createInitialState(this).nextToken();
    }

    public char nextChar() {
        if (isEOF()) {
            return '\0';
        }
        return content[position++];
    }

    public void moveBackIfNotEOF(char current) {
        if (!isEOF(current)) {
            position--;
        }
    }

    @Override
    public boolean isDecimalPoint(char c) {
        return c == '.';
    }

    @Override
    public boolean isSpace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    @Override
    public boolean isChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    @Override
    public boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    @Override
    public boolean isOperator(char c) {
        return (c == '>' || c == '<' || c == '=' || c == '!' || c == '+' || c == '-' || c == '*' || c == '/');
    }

    @Override
    public boolean isEOF() {
        return position == content.length;
    }

    @Override
    public boolean isEOF(char c) {
        return c == '\0';
    }

}
