package com.fisko.interpreter.exceptions;

public class UnknownVariable extends RuntimeException {

    public UnknownVariable(String value) {
        super("Wrong value: " + value);
    }
}
