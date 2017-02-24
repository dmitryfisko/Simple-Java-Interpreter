package com.fisko.interpreter.parser;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.exceptions.UnknownVariable;
import com.fisko.interpreter.parser.models.Variable;

import java.util.*;

public class StateRegistry {

    private static StateRegistry INSTANCE;

    private HashMap<String, Variable> mVariables = new HashMap<>();
    private Stack<HashSet<String>> mDeclarations = new Stack<>();

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
        mDeclarations.add(new HashSet<>());
    }

    public Variable getVariable(String name) {
        return getVariable(new Token(name));
    }

    public Variable getVariable(Token token) {
        String name = token.getToken();
        if (mVariables.containsKey(name)) {
            return mVariables.get(name);
        } else {
            throw new UnknownVariable(token);
        }
    }

    public void declareVariable(Variable variable) {
        String variableName = variable.getName();
        if (mVariables.containsKey(variableName)) {
            throw new RuntimeException();
        }
        mDeclarations.peek().add(variable.getName());
        mVariables.put(variableName, variable);
    }

    public void leaveBlock() {
        for (String variableName : mDeclarations.pop()) {
            mVariables.remove(variableName);
        }
    }

}
