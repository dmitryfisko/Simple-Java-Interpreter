package com.fisko.interpreter.parser.interpret.impl;

import com.fisko.interpreter.parser.interpret.Interpretable;
import com.fisko.interpreter.parser.interpret.TreePrinter;
import com.fisko.interpreter.parser.models.Variable;

public class SqrtMethod extends Interpretable {

    private Expression mExpression;

    public SqrtMethod(Expression expression) {
        mExpression = expression;
    }

    @Override
    public Variable interpret() {
        double value = mExpression.interpret().getNumericValue();
        return new Variable(Math.sqrt(value));
    }

    @Override
    public void println(int level) {
        TreePrinter.println("Math.sqrt", level);
        mExpression.println(level + 1);
    }

}
