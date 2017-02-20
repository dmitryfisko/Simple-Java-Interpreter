package com.fisko.interpreter.parser;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.exceptions.WrongTokenType;

public class TokenInfoProvider {

    public static class BracketInfo {

        public Token openBracket;
        public Token closeBracket;

        public BracketInfo(Token openBracket, Token closeBracket) {
            this.openBracket = openBracket;
            this.closeBracket = closeBracket;
        }
    }

    private static final BracketInfo ROUND_BRACKET_INFO = new BracketInfo(new Token("("), new Token(")"));
    private static final BracketInfo BRACES_INFO = new BracketInfo(new Token("{"), new Token("}"));

    public static BracketInfo getBracketInfo(Token token) {
        switch (token.getType()) {
            case ROUND_BRACKET:
                return ROUND_BRACKET_INFO;
            case BRACES:
                return BRACES_INFO;
            default:
                throw new WrongTokenType(token);
        }
    }

}
