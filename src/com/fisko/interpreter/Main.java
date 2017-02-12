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
        printTokens(tokens);

    }

    private static void printTokens(List<Token> tokens) {
        for (Token token : tokens) {
            System.out.print(token);
            System.out.print(' ');
        }
    }

}
