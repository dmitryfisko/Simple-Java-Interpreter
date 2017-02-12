package com.fisko.interpreter.exceptions;

public class UnrecognizedSymbol extends RuntimeException {

    public UnrecognizedSymbol() {
        super("Unrecognized symbol detected");
    }

}
