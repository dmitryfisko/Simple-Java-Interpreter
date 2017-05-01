package com.fisko.interpreter.analyzer;

import com.fisko.interpreter.exceptions.UnrecognizedToken;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class TokenTyper {

    private static final List<String> TYPES = Arrays.asList(
            "int", "double"
    );
    private static final List<String> OPERATORS = Arrays.asList(
            "++", "--", "==", "!=", "<=", "<", ">", "+", "-", "*", "=", ";", "/", "%"
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
    public static final String VARIABLE_PATTERN = "[A-Za-z]*";
    public static final Pattern VARIABLE_PATTERN_COMPILED = Pattern.compile(VARIABLE_PATTERN);

    private static final List<String> METHODS = Arrays.asList(
            "System.out.println", "System.out.print", "Math.sqrt"
    );

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
        } else if (METHODS.contains(token)) {
            return Token.TokenType.METHOD;
        } else if (VARIABLE_PATTERN_COMPILED.matcher(token).matches() || isNumber(token)) {
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
