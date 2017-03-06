package com.fisko.interpreter.exceptions;

public class VariableAlreadyDeclared extends RuntimeException {

    public VariableAlreadyDeclared(int line) {
        super("Variable already declared at line " + line);
    }
}
