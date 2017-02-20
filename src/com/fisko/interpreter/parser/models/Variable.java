package com.fisko.interpreter.parser.models;

import com.fisko.interpreter.parser.StateRegistry;

public class Variable {

    public enum Type {
        INTEGER,
        DOUBLE,
        UNDEFINED,
        BOOLEAN
    }

    private Type mType;
    private String mValue;
    private String mName;
    private StateRegistry mRegistry;

    public Variable(Double value) {
        this("" + value);
    }

    public Variable(Boolean value) {
        this("" + value);
    }

    public Variable(String value) {
        mType = getType(value);
        mRegistry = StateRegistry.getInstance();

        if (mType == Type.UNDEFINED) {
            mName = value;
        } else {
            mValue = value;
        }
    }

    public Type getType() {
        return mType;
    }

    public String getName() {
        if (mName != null) {
            return mName;
        } else {
            throw new RuntimeException();
        }
    }

    public Variable setValue(Variable value) {
        if (mName != null) {
            Variable variable = StateRegistry.getInstance().getVariable(mName);
            if (variable != this) {
                variable.setValue(value);
                return variable;
            }
        }

        mValue = value.mValue;
        mType = value.mType;
        return this;
    }

    public double getNumericValue() {
        if (mType == Type.UNDEFINED) {
            return mRegistry.getVariable(mName).getNumericValue();
        } else if (mType != Type.UNDEFINED) {
            return Double.parseDouble(mValue);
        } else {
            throw new RuntimeException();
        }
    }

    public boolean getBoolValue() {
        if (mType == Type.UNDEFINED) {
            return mRegistry.getVariable(mName).getBoolValue();
        } else if (mType != Type.UNDEFINED) {
            return Boolean.parseBoolean(mValue);
        } else {
            throw new RuntimeException();
        }
    }

    private Type getType(String value) {
        if (isInteger(value)) {
            return Type.INTEGER;
        } else if (isDouble(value)) {
            return Type.DOUBLE;
        } else if (isBoolean(value)) {
            return Type.BOOLEAN;
        } else {
            return Type.UNDEFINED;
        }
    }

    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isBoolean(String value) {
        return "true".equals(value) || "false".equals(value);
    }

}
