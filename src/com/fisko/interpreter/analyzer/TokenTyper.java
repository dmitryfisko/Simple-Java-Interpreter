package com.fisko.interpreter.analyzer;

import com.fisko.interpreter.exceptions.UnrecognizedToken;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.regex.Pattern;

public class TokenTyper {

    private static final List<String> TYPES = Arrays.asList(
            "int", "double"
    );
    private static final List<String> OPERATORS = Arrays.asList(
            "++", "--", "==", "<=", "<", ">", "+", "-", "*", "=", ";", "/", "%"
    );
    private static final List<String> BLOCKS = Arrays.asList(
            "for", "if", "while", "else"
    );
    private static final List<String> BRACES = Arrays.asList(
            "{", "}"
    );
    private static final List<String> ROUND_BRACKET = Arrays.asList(
            "(", ")"
    );
    private static final List<String> VARIABLE = Arrays.asList(
            "second", "first", "third", "i", "n", "a", "b", "c", "temp"
    );
    public static final String NUMBER_PATTERN = "[0-9].?[0-9]";
    public static final Pattern NUMBER_PATTERN_COMPILED = Pattern.compile(NUMBER_PATTERN);

    private static final String METHOD = "System.out.println";

    public Token.TokenType typify(String token, int line) {
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
        } else if (VARIABLE.contains(token) || isNumber(token)) {
            return Token.TokenType.VARIABLE;
        } else {
            throw new UnrecognizedToken(token, line);
        }
    }

    public boolean isNumber(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

}
