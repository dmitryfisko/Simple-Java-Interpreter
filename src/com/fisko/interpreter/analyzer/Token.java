package com.fisko.interpreter.analyzer;

public class Token {

    public enum TokenType {
        TYPE,
        OPERATOR,
        BLOCK,
        ROUND_BRACKET,
        BRACES,
        METHOD,
        VARIABLE
    }

    private String mToken;
    private TokenType mTokenType;
    private int mLine;

    public Token(String token, int line) {
        mToken = token;
        mLine = line;
        mTokenType = new TokenTyper().typify(token, line);
    }

    private static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public TokenType getType() {
        return mTokenType;
    }

    public int getLine() {
        return mLine;
    }

    private static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

    @Override
    public String toString() {
        return padRight(mToken, 18) + " : " + padLeft(mTokenType.toString(), 15);
    }

}
