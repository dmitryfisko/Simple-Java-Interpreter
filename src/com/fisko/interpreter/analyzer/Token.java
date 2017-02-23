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

    public Token(String token) {
        this(token, 17); // magic number
    }

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

    public String getToken() {
        return mToken;
    }

    public int getLine() {
        return mLine;
    }

    private static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

    public boolean isVariable() {
        return mTokenType == TokenType.VARIABLE;
    }

    public boolean isOperator() {
        return mTokenType == TokenType.OPERATOR;
    }

    @Override
    public String toString() {
        return padRight(mToken, 18) + " : " + padLeft(mTokenType.toString(), 15);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Token == false) {
            return false;
        }
        Token o = (Token) obj;
        return mToken.equals(o.mToken);
    }

    @Override
    public int hashCode() {
        return mToken.hashCode();
    }
}
