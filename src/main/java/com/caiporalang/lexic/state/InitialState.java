package com.caiporalang.lexic.state;

import com.caiporalang.lexic.ScannerContext;
import com.caiporalang.lexic.Token;
import com.caiporalang.lexic.exception.UnrecognizedSymbol;

import static com.caiporalang.lexic.state.States.*;

class InitialState extends ScannerState {

    InitialState(ScannerContext context) {
        super(context, "");
    }

    @Override
    public Token getTokenFromState(char current) {
        if (context.isChar(current)) {
            return createIdentifierState(context, Character.toString(current)).nextToken();
        } else if (context.isDigit(current)) {
            return createNumberState(context, Character.toString(current)).nextToken();
        } else if (context.isSpace(current)) {
            return same().nextToken();
        } else if (context.isOperator(current)) {
            return createOperatorState(context, Character.toString(current)).nextToken();
        } else if (context.isEOF(current)) {
            return Token.getEOF();
        }

        throw new UnrecognizedSymbol(current);
    }

}
