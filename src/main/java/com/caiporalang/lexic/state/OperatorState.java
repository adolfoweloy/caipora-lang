package com.caiporalang.lexic.state;

import com.caiporalang.lexic.ScannerContext;
import com.caiporalang.lexic.Token;
import com.caiporalang.lexic.TokenType;
import com.caiporalang.lexic.exception.InvalidOperator;

class OperatorState extends ScannerState {
    OperatorState(ScannerContext context, String term) {
        super(context, term);
    }

    @Override
    public Token getTokenFromState(char current) {
        if (context.isChar(current) || context.isDigit(current) || context.isSpace(current)) {
            context.moveBackIfNotEOF(current);
            return new Token(TokenType.OPERATOR, getTerm());
        } else {
            throw new InvalidOperator(getTerm() + "[" + current + "]");
        }
    }
}
