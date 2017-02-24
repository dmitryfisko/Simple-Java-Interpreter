package com.fisko.interpreter.exceptions;

import com.fisko.interpreter.parser.models.Variable;

public class WrongVariableType extends RuntimeException {

    public WrongVariableType(Variable.Type resType, Variable.Type expressionType, int line) {
        super("Imposible set " + expressionType + " to variable of type " + resType +  " at line " + line);
    }
}
