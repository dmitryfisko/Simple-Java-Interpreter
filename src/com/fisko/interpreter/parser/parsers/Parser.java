package com.fisko.interpreter.parser.parsers;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.exceptions.IllegalOperator;
import com.fisko.interpreter.exceptions.OperatorsParseException;
import com.fisko.interpreter.exceptions.WrongEnclosedBracketsSequence;
import com.fisko.interpreter.parser.TokenInfoProvider;
import com.fisko.interpreter.parser.TokenInfoProvider.BracketInfo;
import com.fisko.interpreter.parser.interpret.Interpretable;

import java.util.LinkedList;
import java.util.List;

public abstract class Parser {

    private static final int NOT_FOUND = -1;

    public class ParserResult {

        public Interpretable interpretable;
        public List<Token> remainingTokens;

        public ParserResult(Interpretable interpretable, List<Token> remainingTokens) {
            this.interpretable = interpretable;
            this.remainingTokens = remainingTokens;
        }
    }

    public class SplitterHolder {

        public List<Token> extractedTokens;
        public List<Token> remainingTokens;

        public SplitterHolder(List<Token> extractedTokens, List<Token> remainingTokens) {
            this.extractedTokens = new LinkedList<>(extractedTokens);
            this.remainingTokens = new LinkedList<>(remainingTokens);
        }
    }

    public abstract ParserResult parse(List<Token> tokens);

    public abstract boolean isCompatible(Token token);

    protected SplitterHolder splitByToken(Token splitToken, List<Token> tokens) {
        int splitIndex = tokens.indexOf(splitToken);
        if (splitIndex != NOT_FOUND) {
            return createSplitHolder(0, splitIndex, tokens);
        } else {
            throw new OperatorsParseException(splitToken);
        }
    }

    protected SplitterHolder splitByEnclosedToken(Token enclosedToken, List<Token> tokens) {
        int splitIndex = getEnclosedBracketSplitIndex(enclosedToken, tokens);
        return createSplitHolder(1, splitIndex, tokens);
    }

    private int getEnclosedBracketSplitIndex(Token enclosedToken, List<Token> tokens) {
        BracketInfo bracketInfo = TokenInfoProvider.getBracketInfo(enclosedToken);
        int openedBrackets = 0;
        for (int i = 0; i < tokens.size(); ++i) {
            Token token = tokens.get(i);
            if (bracketInfo.openBracket.equals(token)) {
                ++openedBrackets;
            } else if (bracketInfo.closeBracket.equals(token)) {
                --openedBrackets;
            }

            if (openedBrackets < 0) {
                throw new WrongEnclosedBracketsSequence();
            } else if (openedBrackets == 0) {
                return i;
            }
        }
        throw new WrongEnclosedBracketsSequence();
    }

    private SplitterHolder createSplitHolder(int startIndex, int splitIndex, List<Token> tokens) {
        try {
            List<Token> extractedTokens = tokens.subList(startIndex, splitIndex);
            List<Token> remainedTokens = tokens.subList(splitIndex + 1, tokens.size());
            return new SplitterHolder(extractedTokens, remainedTokens);
        } catch (IllegalArgumentException e) {
            throw new IllegalOperator(tokens.get(0));
        }
    }

}
