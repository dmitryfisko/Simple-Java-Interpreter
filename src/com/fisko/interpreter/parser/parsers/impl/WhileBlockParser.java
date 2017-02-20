package com.fisko.interpreter.parser.parsers.impl;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.exceptions.IllegalOperator;
import com.fisko.interpreter.parser.interpret.impl.Expression;
import com.fisko.interpreter.parser.interpret.impl.WhileBlock;
import com.fisko.interpreter.parser.parsers.Parser;

import java.util.List;

public class WhileBlockParser extends Parser {

    private static final Token WHILE_TOKEN = new Token("while");
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
        SplitterHolder conditionSplit = splitByEnclosedToken(BRACKET_TOKEN, tokens);
        Expression condition = new Expression(conditionSplit.extractedTokens);

        ParserResult parserResult = new SimpleBlockParser().parse(conditionSplit.remainingTokens);
        WhileBlock whileBlock = new WhileBlock(condition, parserResult.interpretable);
        return new ParserResult(whileBlock, parserResult.remainingTokens);
    }

    @Override
    public boolean isCompatible(Token token) {
        return WHILE_TOKEN.equals(token);
    }

}
