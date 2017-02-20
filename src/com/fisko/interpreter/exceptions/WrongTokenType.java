package com.fisko.interpreter.exceptions;

import com.fisko.interpreter.analyzer.Token;

public class WrongTokenType extends RuntimeException {

    public WrongTokenType(Token token) {
        super("Token wrong type: " + token);
    }

}
