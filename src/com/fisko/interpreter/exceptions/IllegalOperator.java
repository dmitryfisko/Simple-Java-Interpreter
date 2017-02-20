package com.fisko.interpreter.exceptions;

import com.fisko.interpreter.analyzer.Token;

public class IllegalOperator extends RuntimeException {
    public IllegalOperator(Token token) {
        super("Illegal operator at line " + token.getLine());
    }
}
