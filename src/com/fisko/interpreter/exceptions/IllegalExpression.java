package com.fisko.interpreter.exceptions;

import com.fisko.interpreter.analyzer.Token;

public class IllegalExpression extends RuntimeException {

    public IllegalExpression(Token token) {
        super("Illegal expression at line " + token.getLine());
    }

}
