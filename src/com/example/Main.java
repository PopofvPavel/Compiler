package com.example;

public class Main {

    public static void main(String[] args) {
        LexicAnalyzer lexicAnalyzer = new LexicAnalyzer();
        String inputFilename = args[0];
        String inputString = FileUtils.FileToString(inputFilename);
        lexicAnalyzer.analyze(inputString);
        SymbolTable symbolTable = new SymbolTable(lexicAnalyzer.getSymbols());
        System.out.println(symbolTable);
        FileUtils.writeListOfTokensToFile(symbolTable, args[2]);
        FileUtils.writeListOfTokensToFile(lexicAnalyzer.getTokens(), lexicAnalyzer.getSymbols(), args[1]);
        //FileUtils.writeListOfSymbolsToFile(lexicAnalyzer.getSymbols(), args[2]);


        System.out.println(lexicAnalyzer.splitStringIntoParts(inputString));

    }
}
