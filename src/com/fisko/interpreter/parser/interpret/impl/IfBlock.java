package com.fisko.interpreter.parser.interpret.impl;

import com.fisko.interpreter.parser.StateRegistry;
import com.fisko.interpreter.parser.interpret.Interpretable;
import com.fisko.interpreter.parser.interpret.TreePrinter;
import com.fisko.interpreter.parser.models.Variable;

public class IfBlock extends Interpretable {

    private Interpretable mRightBlock;
    private Interpretable mWrongBlock;
    private Expression mCondition;

    private StateRegistry mState = StateRegistry.getInstance();

    public IfBlock(Expression condition, Interpretable rightBlock, Interpretable wrongBlock) {
        mRightBlock = rightBlock;
        mWrongBlock = wrongBlock;
        mCondition = condition;
    }

    @Override
    public Variable interpret() {
        if (mCondition.interpret().getBoolValue()) {
            mState.declareBlock();
            mRightBlock.interpret();
            mState.leaveBlock();
        } else {
            mState.declareBlock();
            mWrongBlock.interpret();
            mState.leaveBlock();
        }
        return null;
    }

    @Override
    public void println(int level) {
        TreePrinter.println("if", level);
        mCondition.println(level + 1);
        mRightBlock.println(level + 2);
        mWrongBlock.println(level + 2);
    }

}
