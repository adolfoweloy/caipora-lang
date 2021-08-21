package com.caiporalang.lexic.state;

import com.caiporalang.lexic.ScannerContext;

/**
 * Factory of the valid states for the Lexical Analyzer state machine.
 */
public class States {
    public static ScannerState createInitialState(ScannerContext context) {
        return new InitialState(context);
    }

    public static ScannerState createIdentifierState(ScannerContext context, String term) {
        return new IdentifierState(context, term);
    }

    public static ScannerState createNumberState(ScannerContext context, String term) {
        return new NumberState(context, term);
    }

    public static ScannerState createOperatorState(ScannerContext context, String term) {
        return new OperatorState(context, term);
    }

    public static ScannerState createRealNumberState(ScannerContext context, String term) {
        return new RealNumberState(context, term);
    }
}
