package com.fisko.interpreter.parser;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.exceptions.UnknownVariable;
import com.fisko.interpreter.parser.models.Variable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class StateRegistry {

    private static StateRegistry INSTANCE;

    private HashMap<String, Variable> mVariables = new HashMap<>();
    private Stack<LinkedList<String>> mDeclarations = new Stack<>();

    private StateRegistry() {
    }

    public static StateRegistry getInstance() {
        if (INSTANCE == null) {
            synchronized (StateRegistry.class) {
                if (INSTANCE == null) {
                    INSTANCE = new StateRegistry();
                }
            }
        }
        return INSTANCE;
    }

    public void declareBlock() {
        mDeclarations.add(new LinkedList<>());
    }

    public Variable getVariable(String name) {
        return getVariable(new Token(name));
    }

    public Variable getVariable(Token token) {
        String name = token.getToken();
        mDeclarations.peek().add(name);
        if (mVariables.containsKey(name)) {
            return mVariables.get(name);
        } else {
            throw new UnknownVariable(token);
        }
    }

    public void declareVariable(Variable variable) {
        if (mVariables.containsKey(variable.getName())) {
            throw new RuntimeException();
        }
        mVariables.put(variable.getName(), variable);
    }

    public void leaveBlock() {
        for (String variableName : mDeclarations.pop()) {
            mVariables.remove(variableName);
        }
    }

}
