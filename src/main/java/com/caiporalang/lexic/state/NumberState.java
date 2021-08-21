package com.caiporalang.lexic.state;

import com.caiporalang.lexic.ScannerContext;
import com.caiporalang.lexic.Token;
import com.caiporalang.lexic.TokenType;
import com.caiporalang.lexic.exception.MalformedNumber;

import static com.caiporalang.lexic.state.States.createNumberState;
import static com.caiporalang.lexic.state.States.createRealNumberState;

class NumberState extends ScannerState {

    NumberState(ScannerContext context, String term) {
        super(context, term);
    }

    @Override
    public Token getTokenFromState(char current) {
        if (context.isDigit(current)) {
            return createNumberState(context, updateTerm(current)).nextToken();
        } else if (context.isDecimalPoint(current)) {
            return createRealNumberState(context, updateTerm(current)).nextToken();
        } else if (context.isChar(current) || context.isSpace(current)) {
            context.moveBackIfNotEOF(current);
            return new Token(TokenType.NUMBER, getTerm());
        } else {
            throw new MalformedNumber(getTerm() + "[" + current + "]");
        }
    }
}
