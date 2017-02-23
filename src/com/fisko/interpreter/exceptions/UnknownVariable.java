package com.fisko.interpreter.exceptions;

import com.fisko.interpreter.analyzer.Token;

public class UnknownVariable extends RuntimeException {

    public UnknownVariable(Token token) {
        super("Unknown variable: " +  token.getToken() + " on line " + token.getLine());
    }
}
