package com.fisko.interpreter.exceptions;

public class UnrecognizedToken extends RuntimeException {

    public UnrecognizedToken(String token, int line) {
        super("UnrecognizedToken: " + token + " on line " + line);
    }

}
