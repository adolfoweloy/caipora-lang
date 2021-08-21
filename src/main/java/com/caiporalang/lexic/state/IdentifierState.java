package com.caiporalang.lexic.state;

import com.caiporalang.lexic.ScannerContext;
import com.caiporalang.lexic.Token;
import com.caiporalang.lexic.TokenType;
import com.caiporalang.lexic.exception.InvalidIdentifier;

import static com.caiporalang.lexic.state.States.createIdentifierState;

class IdentifierState extends ScannerState {

    IdentifierState(ScannerContext context, String term) {
        super(context, term);
    }

    @Override
    public Token getTokenFromState(char current) {
        if (context.isChar(current) || context.isDigit(current)) {
            return createIdentifierState(context, updateTerm(current)).nextToken();
        } else if (context.isSpace(current) || context.isEOF(current)) {
            context.moveBackIfNotEOF(current);
            return new Token(TokenType.IDENTIFIER, getTerm());
        } else {
            throw new InvalidIdentifier(getTerm() + "[" + current + "]");
        }
    }
}
