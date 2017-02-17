package com.fisko.interpreter.analyzer.extractors;

import com.fisko.interpreter.analyzer.Token;

import java.util.List;

public abstract class TokenAnalyzer {

    private final TokenAnalyzerState mState;

    public TokenAnalyzer(TokenAnalyzerState state) {
        mState = state;
    }

    public int extract(final int startPosition, final String code, final List<Token> tokens) {
        int endPosition = computePatternEndPosition(startPosition, code);
        String token = code.substring(startPosition, endPosition);
        tokens.add(new Token(token, mState.getCurrentLine()));
        return endPosition;
    }

    protected int computePatternEndPosition(final int startPosition, final String code) {
        int endPosition = startPosition;
        while (endPosition < code.length() && isCompatible(code.charAt(endPosition))) {
            ++endPosition;
        }
        return endPosition;
    }

    protected TokenAnalyzerState getState() {
        return mState;
    }

    public abstract boolean isCompatible(final char symbol);

}
