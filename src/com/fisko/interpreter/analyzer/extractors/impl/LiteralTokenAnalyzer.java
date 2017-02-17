package com.fisko.interpreter.analyzer.extractors.impl;

import com.fisko.interpreter.analyzer.extractors.TokenAnalyzer;
import com.fisko.interpreter.analyzer.extractors.TokenAnalyzerState;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LiteralTokenAnalyzer extends TokenAnalyzer {

    private static final String LITERAL_PATTERN = "[0-9a-zA-Z.]";
    private static final Pattern LITERAL_PATTERN_COMPILED = Pattern.compile(LITERAL_PATTERN);

    public LiteralTokenAnalyzer(TokenAnalyzerState state) {
        super(state);
    }

    @Override
    public boolean isCompatible(char symbol) {
        Matcher matcher = LITERAL_PATTERN_COMPILED.matcher("" + symbol);
        return matcher.find();
    }

}
