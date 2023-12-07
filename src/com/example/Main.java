package com.example;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        LexicAnalyzer lexicAnalyzer = new LexicAnalyzer();
        String inputFilename = args[0];
        String inputString = FileUtils.FileToString(inputFilename);
        lexicAnalyzer.analyze(inputString);

       /* SymbolTable symbolTable = new SymbolTable(lexicAnalyzer.getSymbols());
        System.out.println(symbolTable);
        FileUtils.writeListOfTokensToFile(symbolTable, args[2]);
        FileUtils.writeListOfTokensToFile(lexicAnalyzer.getTokens(), lexicAnalyzer.getSymbols(), args[1]);*/

        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer(lexicAnalyzer.getTokens());
        syntaxAnalyzer.git avalidateSyntax();
        SyntaxTree tree = syntaxAnalyzer.getSyntaxTree();

        if (args[4].equalsIgnoreCase("lex")) {
            FileUtils.writeListOfTokensToFile(lexicAnalyzer.getTokens(), lexicAnalyzer.getSymbols(), args[1]);
            FileUtils.writeListOfSymbolsToFile(lexicAnalyzer.getSymbols(), args[2]);
        } else if (args[4].equalsIgnoreCase("syn")) {

            FileUtils.writeStringToFile(tree.toString(), args[3]);
        } else {
            throw new IOException("Неизвестная команда");
        }



        //FileUtils.writeListOfSymbolsToFile(lexicAnalyzer.getSymbols(), args[2]);
        System.out.println(lexicAnalyzer.splitStringIntoParts(inputString));

    }
}
