package com.fisko.interpreter.preprocessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CodeReader {

    private static final char STRING_START_LITERAL = '\"';
    private static final char COMMENT_LITERAL = '/';
    private static final char NEW_LINE_LITERAL = '\n';

    private String mFilePath;

    public CodeReader(String filename) {
        mFilePath = filename;
    }

    public String read() {
        StringBuilder code = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(mFilePath))) {
            String line = reader.readLine();
            while (line != null) {
                code.append(removeComment(line)).append(NEW_LINE_LITERAL);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return code.toString();
    }

    private String removeComment(String line) {
        for (int i = 0; i < line.length() - 1; ++i) {
            char currentChar = line.charAt(i);
            if (currentChar == STRING_START_LITERAL) {
                i = skipStringLiteral(i, line);
                continue;
            }

            char nextChar = line.charAt(i + 1);
            if (currentChar == COMMENT_LITERAL && nextChar == COMMENT_LITERAL) {
                return line.substring(0, i);
            }
        }
        return line;
    }

    private int skipStringLiteral(int position, String line) {
        ++position;
        while (position < line.length() && line.charAt(position) != '\"') {
            ++position;
        }
        return position;
    }
    
}
