package com.fisko.interpreter.parser.parsers.impl;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.exceptions.IllegalOperator;
import com.fisko.interpreter.parser.interpret.impl.Expression;
import com.fisko.interpreter.parser.parsers.Parser;

import java.util.LinkedList;
import java.util.List;

public class ExpressionParser extends Parser {

    @Override
    public ParserResult parse(List<Token> tokens) {
        try {
            return parseUnsafe(tokens);
        } catch (NumberFormatException e) {
            throw new IllegalOperator(tokens.get(0));
        }
    }

    private ParserResult parseUnsafe(List<Token> tokens) {
        Expression mExpression = new Expression(tokens);
        return new ParserResult(mExpression, new LinkedList<>());
    }

    @Override
    public boolean isCompatible(Token token) {
        return true;
    }

}
