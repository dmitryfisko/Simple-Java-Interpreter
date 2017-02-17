package com.fisko.interpreter.analyzer.extractors.impl;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.analyzer.extractors.TokenAnalyzer;
import com.fisko.interpreter.analyzer.extractors.TokenAnalyzerState;

import java.util.List;

public class SkipTokenAnalyzer extends TokenAnalyzer {

    private static final String SKIP_SYMBOLS = " \t\n\r";

    public SkipTokenAnalyzer(TokenAnalyzerState state) {
        super(state);
    }

    @Override
    public int extract(final int startPosition, final String code, final List<Token> tokens) {
        int endPosition = computePatternEndPosition(startPosition, code);
        String extractedCode = code.substring(startPosition, endPosition);
        getState().processNewLines(extractedCode);
        return endPosition;
    }

    @Override
    public boolean isCompatible( final char symbol) {
        return SKIP_SYMBOLS.contains("" + symbol);
    }
}
