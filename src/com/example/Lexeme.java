package com.example;

public enum Lexeme {
    MULTIPLICATION_SIGN("*", "операция умножения", LexemeType.HIGH_PRIORITY_OPERATOR),
    MINUS("-", "операция вычитания", LexemeType.LOW_PRIORITY_OPERATOR),
    PlUS("+", "операция сложения", LexemeType.LOW_PRIORITY_OPERATOR),
    DIVISION_SIGN("/", "операция деления", LexemeType.HIGH_PRIORITY_OPERATOR),
    ID("id", "идентификатор с именем", LexemeType.ID),
    LEFT_BRACKET("(", "открывающая скобка", LexemeType.BRACE),
    RIGHT_BRACKET(")", "закрывающая скобка", LexemeType.BRACE),
    INT_CONST("константа целого типа", LexemeType.CONST),
    FLOAT_CONST("константа вещественного типа", LexemeType.CONST);
    private  String value;
    private final String description;
    private final LexemeType lexemeType;


    public String getValue() {
        return value;
    }

    public LexemeType getLexemeType() {
        return lexemeType;
    }


    public String getDescription() {
        return description;
    }

    Lexeme(String value, String description, LexemeType lexemeType) {
        this.value = value;
        this.description = description;
        this.lexemeType = lexemeType;
    }

    Lexeme(String description, LexemeType lexemeType) {
        this.description = description;
        this.lexemeType = lexemeType;
    }
}
