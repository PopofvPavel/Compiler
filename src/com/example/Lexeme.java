package com.example;

public enum Lexeme {
    MULTIPLICATION_SIGN("*", "операция умножения"),
    MINUS("-", "операция вычитания"),
    PlUS("+", "операция сложения"),
    DIVISION_SIGN("/", "операция деления"),
    ID("id", "идентификатор с именем"),
    LEFT_BRACKET("(", "открывающая скобка"),
    RIGHT_BRACKET(")", "закрывающая скобка"),
    INT_CONST("константа целого типа"),
    FLOAT_CONST("константа вещественного типа");
    private  String value;
    private final String description;

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    Lexeme(String value, String description) {
        this.value = value;
        this.description = description;
    }

    Lexeme(String description) {
        this.description = description;
    }
}
