package com.fisko.interpreter.parser.interpret;

public class TreePrinter {

    private static final String TAB = "...";

    public static void println(String node, int level) {
        for (int i = 0; i < level; ++i) {
            System.out.print(TAB);
        }
        System.out.println(node);
    }

}
