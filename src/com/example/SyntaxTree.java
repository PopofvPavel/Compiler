package com.example;

public class SyntaxTree {
    SyntaxTreeNode root;

    public SyntaxTree(SyntaxTreeNode root) {
        this.root = root;
    }

    private String stringForm(SyntaxTreeNode node, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(4 * i)).append("|---").append(node).append('\n');
        if (node.getLeft() != null) {
            sb.append(stringForm(node.getLeft(), i + 1));
        }
        if (node.getRight() != null) {
            sb.append(stringForm(node.getRight(), i + 1));
        }
        return sb.toString();
    }

    @Override
    public String toString() {;
        return stringForm(root,0);
    }
}
