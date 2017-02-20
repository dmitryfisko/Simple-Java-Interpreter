package com.fisko.interpreter.parser.interpret.impl;

import com.fisko.interpreter.parser.StateRegistry;
import com.fisko.interpreter.parser.interpret.Interpretable;
import com.fisko.interpreter.parser.interpret.TreePrinter;
import com.fisko.interpreter.parser.models.Variable;

import java.util.LinkedList;
import java.util.List;

public class SimpleBlock extends Interpretable {

    private List<Interpretable> mInterprets = new LinkedList<>();
    private StateRegistry mState = StateRegistry.getInstance();

    public void add(Interpretable interpretable) {
        mInterprets.add(interpretable);
    }

    @Override
    public Variable interpret() {
        mState.declareBlock();
        for (Interpretable interpretable : mInterprets) {
            interpretable.interpret();
        }
        mState.leaveBlock();
        return null;
    }

    @Override
    public void println(int offset) {
        TreePrinter.println("simple block", offset);
        for (Interpretable interpretable : mInterprets) {
            interpretable.println(offset + 1);
        }
    }
}
