package com.caiporalang.lexic.state;

import com.caiporalang.lexic.ScannerContext;
import com.caiporalang.lexic.Token;
import com.caiporalang.lexic.TokenType;
import com.caiporalang.lexic.exception.MalformedNumber;

public class RealNumberState extends ScannerState {
    RealNumberState(ScannerContext context, String term) {
        super(context, term);
    }

    @Override
    public Token getTokenFromState(char current) {
        if (context.isDigit(current)) {
            return States.createRealNumberState(context, updateTerm(current)).nextToken();
        } else if (context.isChar(current) || context.isSpace(current)) {
            context.moveBackIfNotEOF(current);
            return new Token(TokenType.NUMBER, getTerm());
        } else if (context.isDecimalPoint(current)) {
            throw new MalformedNumber(getTerm() + "[" + current + "]");
        } else {
            throw new MalformedNumber(getTerm() + "[" + current + "]");
        }
    }
}
