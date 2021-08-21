package com.caiporalang.lexic.state;

import com.caiporalang.lexic.ScannerContext;
import com.caiporalang.lexic.Token;

public abstract class ScannerState  {
    final ScannerContext context;
    private final String term;

    protected ScannerState(ScannerContext context, String term) {
        this.context = context;
        this.term = term;
    }

    public Token nextToken() {
        return getTokenFromState(context.nextChar());
    }

    public abstract Token getTokenFromState(char current);

    ScannerState same() {
        return this;
    }

    String updateTerm(char current) {
        return term + current;
    }

    String getTerm() {
        return term;
    }
}
