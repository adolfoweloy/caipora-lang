package com.caiporalang.lexic;

import java.io.IOException;
import java.net.URL;

public class Lexer {
    public static void main(String[] args) throws IOException {
        new Lexer().start();
    }

    private void start() throws IOException {
        URL resource = getClass().getClassLoader().getResource("input.cr");
        ScannerContext scannerContext = new ScannerContext(resource.getPath());
        Token token;
        while ((token = scannerContext.nextToken()).tokenType() != TokenType.EOF) {
            System.out.println(token);
        }

    }
}
