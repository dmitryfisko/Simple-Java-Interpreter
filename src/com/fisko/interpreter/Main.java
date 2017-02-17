package com.fisko.interpreter;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.analyzer.Tokenizer;
import com.fisko.interpreter.preprocessor.CodeReader;

import java.util.List;

public class Main {

    private static final String CODE_FILE_PATH = "main.txt";

public static void main(String[] args) {
    String code = new CodeReader(CODE_FILE_PATH).read();
    List<Token> tokens = new Tokenizer().tokenize(code);
    checkConditions(tokens);
    printTokens(tokens);
}

    private static void printTokens(List<Token> tokens) {
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    private static void checkConditions(List<Token> tokens) {
        for (int i = 1; i < tokens.size(); ++i) {
            Token.TokenType firstType = tokens.get(i).getType();
            Token.TokenType secondType = tokens.get(i-1).getType();
            if (skipUnRequired(firstType) || skipUnRequired(secondType)) {
                continue;
            }
            if (firstType == secondType) {
                throw  new RuntimeException("Wrong operators sequence on line " + tokens.get(i).getLine());
            }
        }
    }

    public static boolean skipUnRequired(Token.TokenType type) {
        if (Token.TokenType.BRACES == type || Token.TokenType.OPERATOR == type) {
            return true;
        } else {
            return false;
        }
    }

}
