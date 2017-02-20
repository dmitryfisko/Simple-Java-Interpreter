package com.fisko.interpreter.parser.interpret.impl;

import com.fisko.interpreter.parser.StateRegistry;
import com.fisko.interpreter.parser.interpret.Interpretable;
import com.fisko.interpreter.parser.interpret.TreePrinter;
import com.fisko.interpreter.parser.models.Variable;

public class Assignment extends Interpretable {

    private String mVariableName;
    private Expression mExpression;
    private boolean isDeclaration;

    public Assignment(String variableName, Expression expression, boolean declaration) {
        mVariableName = variableName;
        mExpression = expression;
        isDeclaration = declaration;
    }

    @Override
    public Variable interpret() {
        StateRegistry stateRegistry = StateRegistry.getInstance();
        Variable variable;
        if (isDeclaration) {
            variable = new Variable(mVariableName);
            stateRegistry.declareVariable(variable);
        } else {
            variable = stateRegistry.getVariable(mVariableName);
        }
        variable.setValue(mExpression.interpret());
        return null;
    }

    @Override
    public void println(int level) {
        TreePrinter.println(mVariableName + " = ", level);
        mExpression.println(level + 1);
    }
}
