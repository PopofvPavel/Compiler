package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SyntaxAnalyzer {
    private final String ERROR_MESSAGE = "Синтаксическая ошибка! ";
    private final List<SyntaxTreeNode> nodes;
    private final List<Token> tokens;

    public SyntaxAnalyzer(List<Token> tokens) {
        this.tokens = tokens;
        this.nodes = new LinkedList<>();

        for (Token token : tokens) {
            nodes.add(new SyntaxTreeNode(token));
        }
    }


    public void validateSyntax() throws IOException {
        validateBraces();
        validateOperators();

    }

    private void analyzeBraces() {
        List<Lexeme> braceFinder = new ArrayList<>();
        for (SyntaxTreeNode node : nodes) {
            braceFinder.add(node.getValue().getName());
        }

        int closeBrace = braceFinder.indexOf(Lexeme.RIGHT_BRACKET);
        if (closeBrace != -1) {
            int openBrace = braceFinder.subList(0, closeBrace).lastIndexOf(Lexeme.LEFT_BRACKET);
            List<SyntaxTreeNode> noBraceNode = new ArrayList<>(nodes.subList(openBrace + 1, closeBrace));
            unionHighPriorityNodes(noBraceNode);
            unionLowPriorityNodes(noBraceNode);
            for (int i = closeBrace; i >= openBrace; i--) {
                if (!nodes.get(i).isRoot()) {
                    nodes.remove(i);
                }
            }
            analyzeBraces();
        }
    }

    private void unionHighPriorityNodes(List<SyntaxTreeNode> noBraceNode) {
        unionNodes(noBraceNode, LexemeType.HIGH_PRIORITY_OPERATOR);
    }

    private void unionLowPriorityNodes(List<SyntaxTreeNode> noBraceNode) {
        unionNodes(noBraceNode, LexemeType.LOW_PRIORITY_OPERATOR);
    }

    private void unionNodes(List<SyntaxTreeNode> noBraceNode, LexemeType priority) {

        int rootIndex = -1;
        for (int i = 0; i < noBraceNode.size(); i++) {
            if (!noBraceNode.get(i).isRoot() &&
                    noBraceNode.get(i).getValue().getName().getLexemeType() == priority) {
                rootIndex = i;
                break;
            }
        }
        if (rootIndex > 0) {
            SyntaxTreeNode rootNode = noBraceNode.get(rootIndex);
            rootNode.root();
            rootNode.insertLeft(noBraceNode.get(rootIndex - 1));
            rootNode.insertRight(noBraceNode.get(rootIndex + 1));
            noBraceNode.get(rootIndex + 1).noRoot();
            noBraceNode.remove(rootIndex + 1);
            noBraceNode.get(rootIndex - 1).noRoot();
            noBraceNode.remove(rootIndex - 1);
            unionNodes(noBraceNode, priority);
        }
    }


    private void validateOperators() throws IOException {
        int valuesCount = 0;
        //List<Lexeme> lexemes = tokens.stream().map(Token::getName).toList();
        List<Lexeme> lexemes = new ArrayList<>();
        for (Token token : tokens) {
            lexemes.add(token.getName());
        }

        for (int i = 0; i < tokens.size(); i++) {
            if (lexemes.get(i).getLexemeType() == LexemeType.HIGH_PRIORITY_OPERATOR
                    || lexemes.get(i).getLexemeType() == LexemeType.LOW_PRIORITY_OPERATOR) {
                valuesCount--;
            } else if (lexemes.get(i).getLexemeType() != LexemeType.BRACE) {
                valuesCount++;
            }
            if (valuesCount > 1) {
                throw new IOException(ERROR_MESSAGE + tokens.get(i) + " без оператора на позиции " + i);
            }
            if (valuesCount < 0) {
                throw new IOException(ERROR_MESSAGE + tokens.get(i) + " без операнда на позиции " + i);
            }
        }
    }

    private void validateBraces() throws IOException {
        int bracesCount = 0;
        //List<Lexeme> lexemes = tokens.stream().map(Token::getName).toList();
        List<Lexeme> lexemes = new ArrayList<>(tokens.size());
        for (Token token : tokens) {
            lexemes.add(token.getName());
        }

        for (int i = 0; i < tokens.size(); i++) {
            if (lexemes.get(i) == Lexeme.LEFT_BRACKET) {
                bracesCount++;
            } else if (lexemes.get(i) == Lexeme.RIGHT_BRACKET) {
                bracesCount--;
            }
            if (bracesCount < 0) {
                throw new IOException(ERROR_MESSAGE + "<)> без <(> на позиции " + (i + 1));
            }
        }
        if (bracesCount > 0) {
            throw new IOException(ERROR_MESSAGE + "<(> не закрыта на позиции " + lexemes.lastIndexOf(Lexeme.LEFT_BRACKET));
        }
    }

    public SyntaxTree getSyntaxTree() {
        analyzeBraces();
        unionHighPriorityNodes(nodes);
        unionLowPriorityNodes(nodes);
        return new SyntaxTree(nodes.get(0));
    }
}
