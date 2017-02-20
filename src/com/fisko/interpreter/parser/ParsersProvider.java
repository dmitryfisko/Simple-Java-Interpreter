package com.fisko.interpreter.parser;

import com.fisko.interpreter.analyzer.Token;
import com.fisko.interpreter.exceptions.OperatorsParseException;
import com.fisko.interpreter.parser.parsers.Parser;
import com.fisko.interpreter.parser.parsers.impl.*;

import java.util.ArrayList;
import java.util.List;

public class ParsersProvider {

    private List<Parser> mParsers = new ArrayList<>();

    public Parser getCompatibleParser(Token token) {
        createParsers();
        for (Parser parser : mParsers) {
            if (parser.isCompatible(token)) {
                return parser;
            }
        }
        throw new OperatorsParseException(token);
    }

    private List<Parser> createParsers() {
        mParsers.add(new SimpleBlockParser());
        mParsers.add(new ForBlockParser());
        mParsers.add(new WhileBlockParser());
        mParsers.add(new IfBlockParser());
        mParsers.add(new PrintlnMethodParser());
        mParsers.add(new AssignmentParser());
        return mParsers;
    }

}
