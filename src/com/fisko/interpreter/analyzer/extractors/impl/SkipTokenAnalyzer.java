package com.fisko.interpreter.analyzer.extractors.impl;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.analyzer.extractors.TokenAnalyzer;

import java.util.List;

public class SkipTokenAnalyzer extends TokenAnalyzer {

    private static final String SKIP_SYMBOLS = " \t\n\r";

    @Override
    public int extract(final int startPosition, final String code, final List<Token> tokens) {
        return computePatternEndPosition(startPosition, code);
    }

    @Override
    public boolean isCompatible( final char symbol) {
        return SKIP_SYMBOLS.contains("" + symbol);
    }
}
