package com.caiporalang.lexic;

import com.caiporalang.lexic.exception.InvalidIdentifier;
import com.caiporalang.lexic.exception.InvalidOperator;
import com.caiporalang.lexic.exception.MalformedNumber;
import com.caiporalang.lexic.exception.UnrecognizedSymbol;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Scanner {
    private int position;
    private char[] content;
    private int state;

    Scanner(String filePath) throws IOException {
        Path inputPath = FileSystems.getDefault().getPath(filePath);
        String content = new String(Files.readAllBytes(inputPath));
        this.content = content.toCharArray();
        this.position = 0;
    }

    public Token nextToken() {
        char current;
        String term = "";

        if (isEOF()) {
            return Token.getEOF();
        }

        state = 0;
        while (true) {
            current = nextChar();
            switch (state) {
                case 0: // initial state
                    if (isChar(current)) {
                        term += current;
                        this.state = 1;
                    } else if (isDigit(current)) {
                        term += current;
                        this.state = 2;
                    } else if (isSpace(current)) {
                        this.state = 0;
                    } else if (isOperator(current)) {
                        term += current;
                        this.state = 4;
                    } else if (isEOF(current)) {
                        return Token.getEOF();
                    } else {
                        throw new UnrecognizedSymbol(current);
                    }
                    break;

                case 1: // building identifier
                    if (isChar(current) || isDigit(current)) {
                        term += current;
                        this.state = 1;
                    } else if (isSpace(current) || isEOF(current)) {
                        if (!isEOF(current)) back();
                        return new Token(TokenType.IDENTIFIER, term);
                    } else {
                        throw new InvalidIdentifier(term + "[" + current + "]");
                    }
                    break;

                case 2: // building number
                    if (isDigit(current)) {
                        term += current;
                        this.state = 2;
                    } else if (isDecimalPoint(current)) {
                        term += current;
                        this.state = 3;
                    } else if (isChar(current) || isSpace(current)) {
                        if (!isEOF(current)) back();
                        return new Token(TokenType.NUMBER, term);
                    } else {
                        throw new MalformedNumber(term + "[" + current + "]");
                    }
                    break;

                case 3: // building real number
                    if (isDigit(current)) {
                        term += current;
                        this.state = 3;
                    } else if (isChar(current) || isSpace(current)) {
                        if (!isEOF(current)) back();
                        return new Token(TokenType.NUMBER, term);
                    } else if (isDecimalPoint(current)) {
                        throw new MalformedNumber(term + "[" + current + "]");
                    }
                case 4: // building operator
                    if (isChar(current) || isDigit(current) || isSpace(current)) {
                        if (!isEOF(current)) back();
                        return new Token(TokenType.OPERATOR, term);
                    } else {
                        throw new InvalidOperator(term + "[" + current + "]");
                    }
                default:
                    throw new RuntimeException("Invalid state");
            }
        }
    }

    private void back() {
        position--;
    }

    private boolean isDecimalPoint(char current) {
        return current == '.';
    }

    private boolean isSpace(char current) {
        return current == ' ' || current == '\t' || current == '\n' || current == '\r';
    }

    private boolean isChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isOperator(char c) {
        return (c == '>' || c == '<' || c == '=' || c == '!' || c == '+' || c == '-' || c == '*' || c == '/');
    }

    private char nextChar() {
        if (isEOF()) {
            return '\0';
        }
        return content[position++];
    }

    private boolean isEOF() {
        return position == content.length;
    }

    private boolean isEOF(char c) {
        return c == '\0';
    }
}
