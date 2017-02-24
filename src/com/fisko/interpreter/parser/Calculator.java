package com.fisko.interpreter.parser;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.exceptions.IllegalExpression;
import com.fisko.interpreter.parser.models.Variable;

public class Calculator {

    public static Variable evaluate(Token operator, Variable varRight, Variable varLeft) {
        String operation = operator.getToken();
        switch (operation) {
            case "-":
            case "+":
            case "*":
                return Operations.computeNumeric(operation, varLeft, varRight);
            case "==":
            case ">":
            case "<":
                return Operations.computeBoolean(operation, varLeft, varRight);
            default:
                throw new IllegalExpression(operator);
        }
    }

    public static Variable evaluate(Token operator, Variable variable) {
        String operation = operator.getToken();
        switch (operator.getToken()) {
            case "++":
            case "--":
                return Operations.reassignOneParameter(operation, variable);
            case "-":
                return Operations.computeOneParameter(operation, variable);
            default:
                throw new IllegalExpression(operator);
        }
    }

}
