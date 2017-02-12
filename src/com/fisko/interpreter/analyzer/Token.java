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

    public Token(String token) {
        mToken = token;
        mTokenType = new TokenTyper().typify(token);
    }

    @Override
    public String toString() {
        return mToken + " : " + mTokenType + "\n";
    }

}
