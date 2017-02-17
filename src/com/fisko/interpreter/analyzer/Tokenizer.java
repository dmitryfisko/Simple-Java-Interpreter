package com.fisko.interpreter.analyzer;

import com.fisko.interpreter.analyzer.extractors.TokenAnalyzerState;
import com.fisko.interpreter.exceptions.UnrecognizedSymbol;
import com.fisko.interpreter.analyzer.extractors.TokenAnalyzer;
import com.fisko.interpreter.analyzer.extractors.impl.LiteralTokenAnalyzer;
import com.fisko.interpreter.analyzer.extractors.impl.SkipTokenAnalyzer;
import com.fisko.interpreter.analyzer.extractors.impl.SpecialTokenAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private List<TokenAnalyzer> mExtractors = new ArrayList<>();

    public Tokenizer() {
        TokenAnalyzerState state = new TokenAnalyzerState();
        mExtractors.add(new LiteralTokenAnalyzer(state));
        mExtractors.add(new SpecialTokenAnalyzer(state));
        mExtractors.add(new SkipTokenAnalyzer(state));
    }

    public List<Token> tokenize(String code) {
        List<Token> tokens = new ArrayList<>();
        for (int position = 0; position < code.length();) {
            TokenAnalyzer extractor = getCompatibleExtractor(code.charAt(position));
            position = extractor.extract(position, code, tokens);
        }
        return tokens;
    }

    private TokenAnalyzer getCompatibleExtractor(char symbol) {
        for (TokenAnalyzer extractor : mExtractors) {
            if (extractor.isCompatible(symbol)) {
                return extractor;
            }
        }

        System.out.println(symbol);
        throw new UnrecognizedSymbol();
    }

}
