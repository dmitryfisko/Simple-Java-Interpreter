package com.fisko.interpreter.parser.parsers.impl;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.exceptions.IllegalOperator;
import com.fisko.interpreter.parser.interpret.impl.Expression;
import com.fisko.interpreter.parser.interpret.impl.PrintlnMethod;
import com.fisko.interpreter.parser.parsers.Parser;

import java.util.List;

public class PrintlnMethodParser extends Parser {

    private static final Token METHOD_TOKEN = new Token("System.out.println");
    private static final Token BRACKET_TOKEN = new Token("(");

    @Override
    public ParserResult parse(List<Token> tokens) {
        try {
            return parseUnsafe(tokens);
        } catch (NumberFormatException e) {
            throw new IllegalOperator(tokens.get(0));
        }
    }

    private ParserResult parseUnsafe(List<Token> tokens) {
        tokens.remove(0);
        SplitterHolder holder = splitByEnclosedToken(BRACKET_TOKEN, tokens);

        Expression expression = new Expression(holder.extractedTokens);
        PrintlnMethod method = new PrintlnMethod(expression);
        holder.remainingTokens.remove(0);
        return new ParserResult(method, holder.remainingTokens);
    }

    @Override
    public boolean isCompatible(Token token) {
        return METHOD_TOKEN.equals(token);
    }

}
