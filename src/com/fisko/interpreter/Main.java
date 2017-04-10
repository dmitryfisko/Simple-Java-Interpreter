package com.fisko.interpreter;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.analyzer.Tokenizer;
import com.fisko.interpreter.parser.interpret.Interpretable;
import com.fisko.interpreter.parser.parsers.impl.SimpleBlockParser;
import com.fisko.interpreter.preprocessor.CodeReader;

import java.util.List;

public class Main {

//    private static final String CODE_FILE_PATH = "primes.txt";
    private static final String CODE_FILE_PATH = "example.txt";
//    private static final String CODE_FILE_PATH = "primes.txt";

    public static void main(String[] args) {
        //test();
        String code = new CodeReader(CODE_FILE_PATH).read();
        List<Token> tokens = new Tokenizer().tokenize(code);
        printTokens(tokens);
        Interpretable interpretable = new SimpleBlockParser().parse(tokens).interpretable;
        interpretable.println(0);
        interpretable.interpret();
    }

    private static void printTokens(List<Token> tokens) {
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    public static void test() {
        int first = 5;
        double second = 7.0;

        while (first > 0) {
            second = second - 2.1;
            first = first - 1;
        }

        {
            int third = - 5;
            for (int i = 0; i < 10; i++) {
                third = third + (i * first);
                first = first + 1;
            }
            second = second + third;
        }

        if (first == 0) {
            System.out.println(first);
        } else {
            System.out.println(second);
        }
    }

    public static void prime_dividers() {
        int n = 40;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                System.out.println(i);
                n /= i;
            }
        }
    }

}
