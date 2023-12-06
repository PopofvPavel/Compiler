package com.example;

public class Token {
    private final Lexeme name;
    private Object attribute;


    public Token(Lexeme name, Object attribute) {
        this.name = name;
        this.attribute = attribute;
    }

    public Token(Lexeme name) {
        this.name = name;
    }

    public Lexeme getName() {
        return name;
    }

    public Object getAttribute() {
        return attribute;
    }


    @Override
    public String toString() {
        return "<" + switch (name) {
            // здесь Integer как надстройка над Object (хотя по факту можно сделать хотя бы Number)
            case ID -> name.getValue() + "," + ((Integer)attribute + 1); //
            case INT_CONST, FLOAT_CONST -> String.valueOf(this.attribute);
            default -> name.getValue();
        } + "> - " + name.getDescription();
    }
}
