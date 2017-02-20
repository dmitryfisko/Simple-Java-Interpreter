package com.fisko.interpreter.parser.parsers.impl;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.exceptions.IllegalOperator;
import com.fisko.interpreter.parser.interpret.impl.Expression;
import com.fisko.interpreter.parser.interpret.impl.ForBlock;
import com.fisko.interpreter.parser.parsers.Parser;

import java.util.List;

public class ForBlockParser extends Parser {

    private static final Token FOR_TOKEN = new Token("for");
    private static final Token BRACKET_TOKEN = new Token("(");
    private static final Token BRACE_TOKEN = new Token("{");
    private static final Token EXPR_END_TOKEN = new Token(";");

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
        SplitterHolder initSplit = splitByEnclosedToken(BRACKET_TOKEN, tokens);
        ForBlock forBlock = processInit(initSplit.extractedTokens);
        SplitterHolder simpleBlockSplit = splitByEnclosedToken(BRACE_TOKEN, initSplit.remainingTokens);
        ParserResult simpleBlock = new SimpleBlockParser().parse(simpleBlockSplit.extractedTokens);

        forBlock.withBlock(simpleBlock.interpretable);
        return new ParserResult(forBlock, simpleBlockSplit.remainingTokens);
    }

    private ForBlock processInit(List<Token> tokens) {
        ParserResult assignmentResult = new AssignmentParser().parse(tokens);

        SplitterHolder conditionSplit = splitByToken(EXPR_END_TOKEN, assignmentResult.remainingTokens);
        Expression condition = new Expression(conditionSplit.extractedTokens);
        Expression iteration = new Expression(conditionSplit.remainingTokens);

        return new ForBlock(assignmentResult.interpretable, condition, iteration);
    }

    @Override
    public boolean isCompatible(Token token) {
        return FOR_TOKEN.equals(token);
    }

}
