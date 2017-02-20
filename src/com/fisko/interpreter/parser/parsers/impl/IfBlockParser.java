package com.fisko.interpreter.parser.parsers.impl;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.exceptions.IllegalOperator;
import com.fisko.interpreter.parser.interpret.Interpretable;
import com.fisko.interpreter.parser.interpret.impl.Expression;
import com.fisko.interpreter.parser.interpret.impl.IfBlock;
import com.fisko.interpreter.parser.parsers.Parser;

import java.util.List;

public class IfBlockParser extends Parser {

    private static final Token IF_TOKEN = new Token("if");
    private static final Token BRACKET_TOKEN = new Token("(");
    private static final Token BRACE_TOKEN = new Token("{");

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
        SplitterHolder conditionSplit = splitByEnclosedToken(BRACKET_TOKEN, tokens);
        Expression condition = new Expression(conditionSplit.extractedTokens);


        ParserResult rightBlockParser = new SimpleBlockParser().parse(conditionSplit.remainingTokens);
        Interpretable rightBlock = rightBlockParser.interpretable;

        rightBlockParser.remainingTokens.remove(0); // else

        ParserResult wrongBlockParser = new SimpleBlockParser().parse(rightBlockParser.remainingTokens);
        Interpretable wrongBlock = wrongBlockParser.interpretable;
        IfBlock ifBlock = new IfBlock(condition, rightBlock, wrongBlock);
        return new ParserResult(ifBlock, wrongBlockParser.remainingTokens);
    }

    @Override
    public boolean isCompatible(Token token) {
        return IF_TOKEN.equals(token);
    }

}
