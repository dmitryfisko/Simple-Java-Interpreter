package com.fisko.interpreter.analyzer.extractors;

public class TokenAnalyzerState {

    private static final String NEW_LINE_SYMBOL = "\n";
    private static final String EMPTY_LINE = "";

    private int mCurrentLine = 1;

    public void processNewLines(String code) {
        int newLines = code.length() - code.replace(NEW_LINE_SYMBOL, EMPTY_LINE).length();
        mCurrentLine += newLines;
    }

    public int getCurrentLine() {
        return mCurrentLine;
    }
}
