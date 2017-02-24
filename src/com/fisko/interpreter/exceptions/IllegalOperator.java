package com.fisko.interpreter.exceptions;

import com.fisko.interpreter.analyzer.Token;

public class IllegalOperator extends RuntimeException {

    public IllegalOperator() {
        super("Illegal operator");
    }

    public IllegalOperator(Token token) {
        super("Illegal operator at line " + token.getLine());
    }
}
