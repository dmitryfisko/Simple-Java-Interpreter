package com.fisko.interpreter.exceptions;

public class WrongExpressionFormat extends RuntimeException {

    public WrongExpressionFormat() {
        super("Wrong expression format");
    }

}
