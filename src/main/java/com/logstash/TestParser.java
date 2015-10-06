package com.logstash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.logstash.ConfigLexer;
import com.logstash.ConfigParser;

public class TestParser {

    public static void main(String[] args) throws Exception {
        System.out.print("Parsing: ");

        Reader reader = null;
        if (args.length > 0) {
            System.out.println(args[0]);
            reader = new BufferedReader(new FileReader(args[0]));
        } else {
            System.out.println("STDIN");
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        ANTLRInputStream input = new ANTLRInputStream(reader);
        ConfigLexer lexer = new ConfigLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ConfigParser parser = new ConfigParser(tokens);
        parser.setTrace(true);
        ParseTree tree = parser.config();
        System.out.println(tree.toStringTree(parser));
    }
}