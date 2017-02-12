package com.fisko.interpreter.analyzer;

import java.util.Arrays;
import java.util.List;

public class TokenTyper {

    private static final List<String> TYPES = Arrays.asList(
            "int", "double"
    );
    private static final List<String> OPERATORS = Arrays.asList(
            "++", "--", "==", "<", ">", "+", "-", "=", ";"
    );
    private static final List<String> BLOCKS = Arrays.asList(
            "for", "if", "while"
    );
    private static final List<String> BRACES = Arrays.asList(
            "{", "}"
    );
    private static final List<String> ROUND_BRACKET = Arrays.asList(
            "(", ")"
    );
    private static final String METHOD = "System.out.println";

    public Token.TokenType typify(String token) {
        if (TYPES.contains(token)) {
            return Token.TokenType.TYPE;
        } else if (OPERATORS.contains(token)) {
            return Token.TokenType.OPERATOR;
        } else if (BLOCKS.contains(token)) {
            return Token.TokenType.BLOCK;
        } else if (ROUND_BRACKET.contains(token)) {
            return Token.TokenType.ROUND_BRACKET;
        } else if (BRACES.contains(token)) {
            return Token.TokenType.BRACES;
        } else if (METHOD.equals(token)) {
            return Token.TokenType.METHOD;
        } else {
            return Token.TokenType.VARIABLE;
        }
    }

}
