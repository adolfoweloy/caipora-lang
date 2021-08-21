package com.caiporalang.lexic;

import java.io.IOException;
import java.net.URL;

public class Compiler {
    public static void main(String[] args) throws IOException {
        new Compiler().start();
    }

    private void start() throws IOException {
        URL resource = getClass().getClassLoader().getResource("input.cr");
        Scanner scanner = new Scanner(resource.getPath());
        Token token;
        while ((token = scanner.nextToken()).tokenType() != TokenType.EOF) {
            System.out.println(token);
        }

    }
}
