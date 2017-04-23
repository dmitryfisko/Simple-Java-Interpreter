package com.fisko.interpreter.analyzer.extractors.impl;

import com.fisko.interpreter.analyzer.extractors.TokenAnalyzer;
import com.fisko.interpreter.analyzer.extractors.TokenAnalyzerState;

public class SpecialTokenAnalyzer extends TokenAnalyzer {

    private static final String SPECIAL_SYMBOLS = "!s;,(){}=+-*%/<>";
    private static final String[] OPERATORS = {
            "++", "--", "==", "!=", "<="
    };

    public SpecialTokenAnalyzer(TokenAnalyzerState state) {
        super(state);
    }

    @Override
    protected int computePatternEndPosition(final int startPosition, final String code) {
        for (String operator : OPERATORS) {
            if (code.startsWith(operator, startPosition)) {
                return startPosition + operator.length();
            }
        }

        int endPosition = startPosition;
        if (endPosition < code.length() && isCompatible(code.charAt(endPosition))) {
            ++endPosition;
        }
        return endPosition;
    }

    @Override
    public boolean isCompatible(final char symbol) {
        return SPECIAL_SYMBOLS.contains("" + symbol) ;
    }

}
