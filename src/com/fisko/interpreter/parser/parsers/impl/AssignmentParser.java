package com.fisko.interpreter.parser.parsers.impl;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.exceptions.IllegalOperator;
import com.fisko.interpreter.parser.interpret.impl.Assignment;
import com.fisko.interpreter.parser.interpret.impl.Expression;
import com.fisko.interpreter.parser.parsers.Parser;

import java.util.List;

public class AssignmentParser extends Parser {


    @Override
    public ParserResult parse(List<Token> tokens) {
        try {
            return parseUnsafe(tokens);
        } catch (NumberFormatException e) {
            throw new IllegalOperator(tokens.get(0));
        }
    }

    private ParserResult parseUnsafe(List<Token> tokens) {
        Token variableToken = tokens.remove(0);
        boolean declaration = false;
        if (variableToken.getType() == Token.TokenType.TYPE) {
            variableToken = tokens.remove(0);
            declaration = true;
        }

        // remove operator
        tokens.remove(0);

        SplitterHolder holder = splitByToken(new Token(";"), tokens);
        Expression expression = new Expression(holder.extractedTokens);
        Assignment assignment = new Assignment(variableToken, expression, declaration);
        return new ParserResult(assignment, holder.remainingTokens);
    }

    @Override
    public boolean isCompatible(Token token) {
        switch (token.getType()) {
            case VARIABLE:
            case TYPE:
                return true;
            default:
                return false;
        }
    }

}
