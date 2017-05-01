package com.fisko.interpreter.parser.interpret.impl;

import com.fisko.interpreter.parser.interpret.Interpretable;
import com.fisko.interpreter.parser.interpret.TreePrinter;
import com.fisko.interpreter.parser.models.Variable;

public class PrintlnMethod extends Interpretable {

    private Expression mExpression;
    private boolean mNewLine;

    public PrintlnMethod(Expression expression, boolean isNewLine) {
        mExpression = expression;
        mNewLine = isNewLine;
    }

    @Override
    public Variable interpret() {
        if (mExpression == null) {
            if (mNewLine) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
            return null;
        }
        double value = mExpression.interpret().getNumericValue();
        int intValue = (int) value;
        if (value == intValue) {
            if (mNewLine) {
                System.out.println(intValue);
            } else {
                System.out.print("" + intValue + " ");
            }
        } else {
            if (mNewLine) {
                System.out.println(value);
            } else {
                System.out.print("" + value + " ");
            }
        }
        return null;
    }

    @Override
    public void println(int level) {
        TreePrinter.println("System.out.println", level);
        if (mExpression != null) {
            mExpression.println(level + 1);
        }
    }

}
