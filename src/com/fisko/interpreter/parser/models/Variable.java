package com.fisko.interpreter.parser.models;

import com.fisko.interpreter.exceptions.WrongVariableType;
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

    public Variable(Double value, Type type) {
        this("" + value, type);
    }

    public Variable(Boolean value) {
        this("" + value);
    }

    public Variable(boolean value, Type type) {
        this("" + value, type);
    }

    public Variable(String value) {
        this(value, getType(value));
    }

    public Variable(String value, Type type) {
        mValue = value;
        mType = type;
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

        if (mType == Type.UNDEFINED && value.mType != Type.UNDEFINED ||
                mType == Type.BOOLEAN && value.mType == Type.BOOLEAN ||
                mType == Type.INTEGER && value.mType == Type.INTEGER ||
                mType == Type.DOUBLE && (value.mType == Type.DOUBLE || value.mType == Type.INTEGER)) {
            mValue = value.mValue;
            mType = value.mType;
            return this;
        } else {
            throw new WrongVariableType(mType, value.mType, 16);
        }
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

    private static Type getType(String value) {
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

    private static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isBoolean(String value) {
        return "true".equals(value) || "false".equals(value);
    }

}
