package com.example;

public class SyntaxTreeNode {
    private Token value;
    private SyntaxTreeNode left;
    private SyntaxTreeNode right;
    private boolean root = false;

    public SyntaxTreeNode(Token value) {
        this.value = value;
    }

    public boolean isRoot() {
        return root;
    }

    public void root() {
        root = true;
    }

    public void noRoot() {
        root = false;
    }

    public Token getValue() {
        return value;
    }

    public SyntaxTreeNode getLeft() {
        return left;
    }

    public SyntaxTreeNode getRight() {
        return right;
    }

    public void insertLeft(SyntaxTreeNode node) {
        left = node;
    }

    public void insertRight(SyntaxTreeNode node) {
        right = node;
    }


    @Override
    public String toString() {
        return value.toString();
    }

}
