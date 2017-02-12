package com.fisko.interpreter.exceptions;

public class UnrecognizedPrimitive extends RuntimeException {

    public UnrecognizedPrimitive() {
        super("Unrecognized symbol detected");
    }

}
