package com.fisko.interpreter.parser.parsers.impl;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.exceptions.IllegalOperator;
import com.fisko.interpreter.parser.interpret.impl.Expression;
import com.fisko.interpreter.parser.interpret.impl.PrintlnMethod;
import com.fisko.interpreter.parser.parsers.Parser;

import java.util.List;

public class PrintlnMethodParser extends Parser {

    private static final Token METHOD_TOKEN = new Token("System.out.println");
    private static final Token METHOD_TOKEN_SINGLE_LINE = new Token("System.out.print");
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
        Token methodToken = tokens.remove(0);
        SplitterHolder holder = splitByEnclosedToken(BRACKET_TOKEN, tokens);

        Expression expression = null;
        if (holder.extractedTokens.size() != 0) {
            expression = new Expression(holder.extractedTokens);
        }
        holder.remainingTokens.remove(0);
        PrintlnMethod method = new PrintlnMethod(expression, isNewLine(methodToken));
        return new ParserResult(method, holder.remainingTokens);
    }

    private boolean isNewLine(Token methodToken) {
        return METHOD_TOKEN.equals(methodToken);
    }

    @Override
    public boolean isCompatible(Token token) {
        return METHOD_TOKEN.equals(token) || METHOD_TOKEN_SINGLE_LINE.equals(token);
    }

}
