package com.fisko.interpreter.parser.interpret.impl;

import com.fisko.interpreter.parser.StateRegistry;
import com.fisko.interpreter.parser.interpret.Interpretable;
import com.fisko.interpreter.parser.interpret.TreePrinter;
import com.fisko.interpreter.parser.models.Variable;

public class ForBlock extends Interpretable {

    private Interpretable mAssignment;
    private Expression mCondition;
    private Expression mIteration;
    private Interpretable mBlock;

    private StateRegistry mState = StateRegistry.getInstance();

    public ForBlock(Interpretable assignment, Expression condition, Expression iteration) {
        mAssignment = assignment;
        mCondition = condition;
        mIteration = iteration;
    }

    public ForBlock withBlock(Interpretable block) {
        mBlock = block;
        return this;
    }

    @Override
    public Variable interpret() {
        mState.declareBlock();
        for (mAssignment.interpret(); mCondition.interpret().getBoolValue(); mIteration.interpret()) {
            mState.declareBlock();
            mBlock.interpret();
            mState.leaveBlock();
        }
        mState.leaveBlock();
        return null;
    }

    @Override
    public void println(int level) {
        TreePrinter.println("for", level);
        mAssignment.println(level + 1);
        mCondition.println(level + 1);
        mIteration.println(level + 1);
        mBlock.println(level + 2);
    }

}
