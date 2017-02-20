package com.fisko.interpreter.parser.interpret.impl;

import com.fisko.interpreter.parser.StateRegistry;
import com.fisko.interpreter.parser.interpret.Interpretable;
import com.fisko.interpreter.parser.interpret.TreePrinter;
import com.fisko.interpreter.parser.models.Variable;

public class WhileBlock extends Interpretable {

    private Expression mCondition;
    private Interpretable mBlock;
    private StateRegistry mState = StateRegistry.getInstance();

    public WhileBlock(Expression condition, Interpretable block) {
        mCondition = condition;
        mBlock = block;
    }


    @Override
    public Variable interpret() {
        mState.declareBlock();
        while (mCondition.interpret().getBoolValue()) {
            mBlock.interpret();
        }
        mState.leaveBlock();
        return null;
    }

    @Override
    public void println(int offset) {
        TreePrinter.println("while", offset);
        mCondition.println(offset + 1);
        mBlock.println(offset + 2);
    }

}
