package com.fisko.interpreter.parser.parsers.impl;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.exceptions.IllegalOperator;
import com.fisko.interpreter.parser.ParsersProvider;
import com.fisko.interpreter.parser.interpret.impl.SimpleBlock;
import com.fisko.interpreter.parser.parsers.Parser;

import java.util.ArrayList;
import java.util.List;

public class SimpleBlockParser extends Parser {

    private static final Token OPEN_BRACES = new Token("{");

    private ParsersProvider mParsers = new ParsersProvider();
    private SimpleBlock mSimpleBlock = new SimpleBlock();

    @Override
    public ParserResult parse(final List<Token> tokens) {
        try {
            return parseUnsafe(tokens);
        } catch (NumberFormatException e) {
            throw new IllegalOperator(tokens.get(0));
        }
    }

    private ParserResult parseUnsafe(List<Token> tokens) {
        SplitterHolder split = getSplit(tokens);
        extractInterpretables(split.extractedTokens);
        return new ParserResult(mSimpleBlock, split.remainingTokens);
    }

    private SplitterHolder getSplit(final List<Token> tokens) {
        if (OPEN_BRACES.equals(tokens.get(0))) {
            return splitByEnclosedToken(tokens.get(0), tokens);
        } else {
            return new SplitterHolder(tokens, new ArrayList<>());
        }
    }

    private void extractInterpretables(List<Token> tokens) {

        while (tokens.size() != 0) {
            //Logger.getGlobal().info(tokens.get(0).toString());
            Token token = tokens.get(0);
            Parser parser = mParsers.getCompatibleParser(token);
            ParserResult result = parser.parse(tokens);
            tokens = result.remainingTokens;
            mSimpleBlock.add(result.interpretable);
        }
    }

    @Override
    public boolean isCompatible(Token token) {
        return OPEN_BRACES.equals(token);
    }
}
