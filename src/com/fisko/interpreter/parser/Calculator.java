package com.fisko.interpreter.parser;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.exceptions.IllegalExpression;
import com.fisko.interpreter.parser.models.Variable;

public class Calculator {

    public static Variable evaluate(Token operator, Variable varRight, Variable varLeft) {
        double valueL = varLeft.getNumericValue();
        double valueR = varRight.getNumericValue();
        switch (operator.getToken()) {
            case "-":
                return new Variable(valueL - valueR);
            case "+":
                return new Variable(valueL + valueR);
            case "*":
                return new Variable(valueL * valueR);
            case "==":
                return new Variable(valueL == valueR);
            case ">":
                return new Variable(valueL > valueR);
            case "<":
                return new Variable(valueL < valueR);
            default:
                throw new IllegalExpression(operator);
        }
    }

    public static Variable evaluate(Token operator, Variable left) {
        double value = left.getNumericValue();
        switch (operator.getToken()) {
            case "++":
                return left.setValue(new Variable(value + 1));
            case "--":
                return left.setValue(new Variable(value - 1));
            case "-":
                return left.setValue(new Variable(-value));
            default:
                throw new IllegalExpression(operator);
        }
    }

}
