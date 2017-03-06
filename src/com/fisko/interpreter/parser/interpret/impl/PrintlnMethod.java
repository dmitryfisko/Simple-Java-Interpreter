package com.fisko.interpreter.parser.interpret.impl;

import com.fisko.interpreter.parser.interpret.Interpretable;
import com.fisko.interpreter.parser.interpret.TreePrinter;
import com.fisko.interpreter.parser.models.Variable;

public class PrintlnMethod extends Interpretable {

    private Expression mExpression;

    public PrintlnMethod(Expression expression) {
        mExpression = expression;
    }

    @Override
    public Variable interpret() {
        double value = mExpression.interpret().getNumericValue();
        int intValue = (int)value;
        if (value == intValue) {
            System.out.println(intValue);
        } else {
            System.out.println(value);
        }
        return null;
    }

    @Override
    public void println(int level) {
        TreePrinter.println("System.put.println", level);
        mExpression.println(level + 1);
    }

}
