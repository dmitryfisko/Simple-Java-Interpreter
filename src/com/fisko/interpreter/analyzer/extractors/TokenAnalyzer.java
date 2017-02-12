package com.fisko.interpreter.analyzer.extractors;

import com.fisko.interpreter.analyzer.Token;

import java.util.List;

public abstract class TokenAnalyzer {

    public int extract(final int startPosition, final String code, final List<Token> tokens) {
        int endPosition = computePatternEndPosition(startPosition, code);
        tokens.add(new Token(code.substring(startPosition, endPosition)));
        return endPosition;
    }

    protected int computePatternEndPosition(final int startPosition, final String code) {
        int endPosition = startPosition;
        while (endPosition < code.length() && isCompatible(code.charAt(endPosition))) {
            ++endPosition;
        }
        return endPosition;
    }

    public abstract boolean isCompatible(final char symbol);

}
