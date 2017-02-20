package com.fisko.interpreter.exceptions;

import com.fisko.interpreter.analyzer.Token;

public class OperatorsParseException extends RuntimeException {

    public OperatorsParseException(Token token) {
        super("Expected token not found: " + token.getToken() + " at line " + token.getLine());
    }

}
