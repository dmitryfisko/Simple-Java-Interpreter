package com.fisko.interpreter.parser.interpret;

import com.fisko.interpreter.parser.models.Variable;

public abstract class Interpretable {

    public abstract Variable interpret();

    public abstract void println(int offset);

}
