package com.fisko.interpreter.parser.interpret.impl;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.exceptions.IllegalExpression;
import com.fisko.interpreter.parser.Calculator;
import com.fisko.interpreter.parser.interpret.Interpretable;
import com.fisko.interpreter.parser.interpret.TreePrinter;
import com.fisko.interpreter.parser.models.Variable;

import java.util.*;

public class Expression extends Interpretable {

    private static final Token MULTIPLY_TOKEN = new Token("*");
    private static final Token DIV_TOKEN = new Token("/");
    private static final Token MOD_TOKEN = new Token("%");
    private static final Token SUM_TOKEN = new Token("+");
    private static final Token SUB_TOKEN = new Token("-");
    private static final Token OPEN_ROUND_BRACKET = new Token("(");
    private static final Token CLOSE_ROUND_BRACKET = new Token(")");
    private static final Token EQUAL_TOKEN = new Token("==");
    private static final Token LESS_TOKEN = new Token("<");
    private static final Token MORE_TOKEN = new Token(">");
    private static final Token LESS_OR_MORE_TOKEN = new Token("<=");
    private static final Token INCREMENT_TOKEN = new Token("--");
    private static final Token DECREMENT_TOKEN = new Token("++");

    private static Map<Token, Integer> PRIORITIES = new HashMap<Token, Integer>() {{
        put(MULTIPLY_TOKEN, 3);
        put(DIV_TOKEN, 3);
        put(MOD_TOKEN, 3);
        put(SUM_TOKEN, 2);
        put(SUB_TOKEN, 2);
        put(OPEN_ROUND_BRACKET, 1);
        put(EQUAL_TOKEN, 0);
        put(LESS_TOKEN, 0);
        put(MORE_TOKEN, 0);
        put(LESS_OR_MORE_TOKEN, 0);
        put(INCREMENT_TOKEN, -1);
        put(DECREMENT_TOKEN, -1);
    }};

    private List<Token> mTokens;

    public Expression(List<Token> tokens) {
        mTokens = tokens;
    }

    private List<Token> convertToRPN(List<Token> expression) {
        //System.out.println("---   " + expression.size());
        List<Token> rpn = new LinkedList<>();
        Stack<Token> stack = new Stack<>();

        for (Token token : expression) {
            if (OPEN_ROUND_BRACKET.equals(token)) {
                stack.push(token);
                continue;
            }

            if (CLOSE_ROUND_BRACKET.equals(token)) {
                while (!OPEN_ROUND_BRACKET.equals(stack.peek())) {
                    rpn.add(stack.pop());
                }
                stack.pop();
                continue;
            }

            if (PRIORITIES.containsKey(token)) {
                while (!stack.empty() && PRIORITIES.get(token) <= PRIORITIES.get(stack.peek())) {
                    rpn.add(stack.pop());
                }
                stack.push(token);
                continue;
            }

            if (token.isVariable()) {
                rpn.add(token);
                continue;
            }

            throw new IllegalExpression(expression.get(0));
        }
        // at the end, pop all the elements in stack to rpn
        while (!stack.isEmpty()) {
            rpn.add(stack.pop());
        }

        return rpn;
    }

    private Variable evalRPN(List<Token> rpn) {
        Stack<Variable> stack = new Stack<>();
        for (Token token : rpn) {
            if (token.isOperator()) {
                //just hack
                if (stack.size() == 1) {
                    stack.push(Calculator.evaluate(token, stack.pop()));
                } else {
                    stack.push(Calculator.evaluate(token, stack.pop(), stack.pop()));
                }
            } else {
                stack.push(new Variable(token.getToken()));
            }
        }

        if (stack.size() != 1) {
            throw new RuntimeException();
        }

        return stack.pop();
    }

    @Override
    public Variable interpret() {
        List<Token> rpn = convertToRPN(mTokens);
        try {
            return evalRPN(rpn);
        } catch (EmptyStackException e) {
            throw new IllegalExpression(rpn.get(0));
        }
    }

    @Override
    public void println(int level) {
        List<Token> rpn = convertToRPN(mTokens);
        String representation = "";
        for (Token token : rpn) {
            representation += token.getToken() + " ";
        }
        TreePrinter.println(representation, level);
    }

}
