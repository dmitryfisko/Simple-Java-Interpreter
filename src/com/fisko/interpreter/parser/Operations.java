package com.fisko.interpreter.parser;

import com.fisko.interpreter.exceptions.IllegalOperator;
import com.fisko.interpreter.parser.models.Variable;

class Operations {

    private static final double EPSILON = 1e-10;

    static Variable computeNumeric(String operation, Variable leftVar, Variable rightVar) {
        Variable.Type resultType = getNumericOperationResultType(leftVar, rightVar);
        double resultValue =  getNumericResultValue(operation, leftVar, rightVar);
        return new Variable(resultValue, resultType);
    }

    static Variable computeBoolean(String operation, Variable leftVar, Variable rightVar) {
        Variable.Type resultType = Variable.Type.BOOLEAN;
        boolean resultValue =  getBooleanResultValue(operation, leftVar, rightVar);
        return new Variable(resultValue, resultType);
    }

    static Variable reassignOneParameter(String operation, Variable variable) {
        Variable computedVariable = computeOneParameter(operation, variable);
        variable.setValue(computedVariable);
        return computedVariable;
    }

    static Variable computeOneParameter(String operation, Variable variable) {
        Variable.Type resultType = getOneParameterOperationResultType(variable);
        double resultValue =  getOneParameterOperationResultValue(operation, variable);
        return new Variable(resultValue, resultType);
    }

    private static double getNumericResultValue(String operation, Variable leftVar, Variable rightVar) {
        Double valueLeft = leftVar.getNumericValue();
        Double valueRight = rightVar.getNumericValue();
        switch (operation) {
            case "+":
                return valueLeft + valueRight;
            case "-":
                return valueLeft - valueRight;
            case "*":
                return valueLeft * valueRight;
            case "%":
                return valueLeft % valueRight;
            case "/":
                return valueLeft / valueRight;
            default:
                throw new IllegalOperator();
        }
    }

    private static boolean getBooleanResultValue(String operation, Variable leftVar, Variable rightVar) {
        Double valueLeft = leftVar.getNumericValue();
        Double valueRight = rightVar.getNumericValue();
        switch (operation) {
            case "==":
                return Math.abs(valueLeft - valueRight) < EPSILON;
            case "!=":
                return Math.abs(valueLeft - valueRight) >= EPSILON;
            case ">":
                return valueLeft > valueRight;
            case "<":
                return valueLeft < valueRight;
            case "<=":
                return valueLeft <= valueRight;
            default:
                throw new IllegalOperator();
        }
    }

    private static double getOneParameterOperationResultValue(String operation, Variable variable) {
        double value = variable.getNumericValue();
        switch (operation) {
            case "++":
                return ++value;
            case "--":
                return --value;
            case "-":
                return -value;
            default:
                throw new IllegalOperator();
        }
    }

    private static Variable.Type getOneParameterOperationResultType(Variable variable) {
        if (isDouble(variable)) {
            return Variable.Type.DOUBLE;
        } else {
            return Variable.Type.INTEGER;
        }
    }

    private static Variable.Type getNumericOperationResultType(Variable left, Variable right) {
        if (isDouble(left) || isDouble(right)) {
            return Variable.Type.DOUBLE;
        } else {
            return Variable.Type.INTEGER;
        }
    }

    private static boolean isDouble(Variable variable) {
        return variable.getType() == Variable.Type.BOOLEAN;
    }

}
